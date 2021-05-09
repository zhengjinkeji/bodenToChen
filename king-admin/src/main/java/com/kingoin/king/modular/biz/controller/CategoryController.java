package com.kingoin.king.modular.biz.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingoin.king.core.base.controller.BaseController;
import com.kingoin.king.core.base.tips.Tip;
import com.kingoin.king.core.cache.CacheKit;
import com.kingoin.king.core.common.constant.cache.Cache;
import com.kingoin.king.core.common.exception.BizExceptionEnum;
import com.kingoin.king.core.exception.KingException;
import com.kingoin.king.core.shiro.ShiroKit;
import com.kingoin.king.core.util.ToolUtil;
import com.kingoin.king.modular.biz.model.Category;
import com.kingoin.king.modular.biz.service.ICategoryService;
import com.kingoin.king.modular.biz.warpper.CategoryWarpper;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年11月19日
 * @time 下午1:45:03
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

	private static String PREFIX = "/biz/category";

//	@Autowired
//	private KingProperties kingProperties;

	@Autowired
	private ICategoryService categoryService;

	/**
	 * 跳转到类别列表页面
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "/category.html";
	}

	/**
	 * 获取类别列表
	 */

	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String name) {

		List<Map<String, Object>> list = this.categoryService.selectCategory(super.getPara("name"));

		return super.warpObject(new CategoryWarpper(list));
	}

	/**
	 * 转到添加页面
	 */
	@RequestMapping(value = "/category_add")
	public String categoryAdd(Model model) {
		List<Map<String, Object>> categorys = this.categoryService.selectCategoryLevels();
		model.addAttribute("categorys", categorys);
		return PREFIX + "/category_add.html";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Tip add(@Valid Category category, BindingResult result, @RequestParam(required = false) String picName) {

		String pname = "";
		if (category.getPid() != null) {

			if (category.getPid() == 0) {
				pname = "顶级";
			} else {
				Category c = this.categoryService.selectById(category.getPid());
				pname = c.getName();
			}
		}
		category.setIcon(picName);
		category.setPname(pname);
		category.setDateAdd(new Date());
		category.setAddUser(ShiroKit.getUser().getName());
		// category.setPic(picName);
		this.categoryService.insert(category);
		return SUCCESS_TIP;
	}

	/**
	 * 跳转到修改页面
	 */
	@RequestMapping(value = "/category_edit/{categoryId}")
	public String categoryEdit(@PathVariable Integer categoryId, Model model) {
		if (ToolUtil.isEmpty(categoryId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Category category = this.categoryService.selectById(categoryId);
		List<Map<String, Object>> categorys = this.categoryService.selectCategoryLevels();
		for (int i = 0; i < categorys.size(); i++) {
			Integer id = (Integer) categorys.get(i).get("id");
			if (id != null && id == categoryId) {
				categorys.remove(i);
			}
		}
		model.addAttribute("categorys", categorys);
		model.addAttribute(category);
		return PREFIX + "/category_edit.html";
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Tip edit(@Valid Category category, BindingResult result, @RequestParam(required = false) String picName,
			@RequestParam(required = false) Boolean picEdit) {
		if (result.hasErrors()) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}

		if (picEdit && picName != null && !"".equals(picName)) {// 修改图片
			Category c = this.categoryService.selectById(category.getId());
			c.setIcon(picName);
			this.categoryService.updateById(c);
			// 记录
			// this.operationService.insertBizOperation(c.getIcon(), picName,
			// "CategoryController+edit+categoryId="+category.getId(),ShiroKit.getUser().getName());

		} else {// 修改内容
			if (picName != null && !"".equals(picName)) {
				category.setIcon(picName);

			}

			String pname = "";
			if (category.getPid() != null) {

				if (category.getPid() == 0) {
					pname = "顶级";
				} else {
					Category c = this.categoryService.selectById(category.getPid());
					pname = c.getName();
				}
			}
			category.setPname(pname);
			category.setDateUpdate(new Date());
			category.setUpdateUser(ShiroKit.getUser().getName());
			this.categoryService.updateById(category);
		}

		// 删除缓存
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
	}

	/**
	 * 跳转到更新图片页面
	 */
	@RequestMapping(value = "/category_edit_pic/{categoryId}")
	public String editPic(@PathVariable Integer categoryId, Model model) {
		if (ToolUtil.isEmpty(categoryId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Category category = this.categoryService.selectById(categoryId);
		model.addAttribute(category);
		model.addAttribute("icon", category.getIcon());
		return PREFIX + "/category_edit_pic.html";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/remove")
	@ResponseBody
	public Tip remove(@RequestParam Integer categoryId) {
		if (ToolUtil.isEmpty(categoryId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		/*
		 * 逻辑删除代码 Category category = this.categoryService.selectById(categoryId);
		 * category.setDateUpdate(new Date());
		 * category.setUpdateUser(ShiroKit.getUser().getName()); category.setStatus(3);
		 * this.categoryService.updateById(category);
		 */
		// 删除
		this.categoryService.deleteById(categoryId);
		// 查询此分类是否有下级
		List<Category> categorys = this.categoryService.selectCategoryByPid(categoryId);
		for (Category c : categorys) {
			/*
			 * 逻辑删除下级 c.setDateUpdate(new Date());
			 * c.setUpdateUser(ShiroKit.getUser().getName()); c.setStatus(3);
			 * this.categoryService.updateById(c);
			 */
			// 删除下级
			this.categoryService.deleteById(c.getId());
		}
		// 删除缓存
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
	}

}

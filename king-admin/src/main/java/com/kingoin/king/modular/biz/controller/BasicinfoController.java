package com.kingoin.king.modular.biz.controller;

import java.text.DecimalFormat;
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
import com.kingoin.king.core.common.constant.factory.ConstantFactory;
import com.kingoin.king.core.common.exception.BizExceptionEnum;
import com.kingoin.king.core.exception.KingException;
import com.kingoin.king.core.node.ZTreeNode;
import com.kingoin.king.core.shiro.ShiroKit;
import com.kingoin.king.core.util.ToolUtil;
import com.kingoin.king.modular.biz.dto.NumSpec;
import com.kingoin.king.modular.biz.model.Basicinfo;
import com.kingoin.king.modular.biz.model.Picture;
import com.kingoin.king.modular.biz.service.IBasicinfoService;
import com.kingoin.king.modular.biz.service.ICategoryService;
import com.kingoin.king.modular.biz.warpper.BasicinfoWapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kingoin.king.core.common.constant.factory.PageFactory;
/**
 * <p>
 * 商品控制器
 * </p>
 * 
 * @author serson
 * @date 2019年11月21日
 * @time 下午1:34:23
 */
@Controller
@RequestMapping("/basicinfo")
public class BasicinfoController extends BaseController {

	private static String PREFIX = "/biz/basicinfo";

	// @Autowired
	// private IOperationService operationService;

	@Autowired
	private IBasicinfoService basicinfoService;

	@Autowired
	private ICategoryService categoryService;

	/**
	 * 跳转到品牌商品列表页面
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "/basicinfo.html";
	}
	/**
	 * 跳转到喜豆商品列表页面
	 */
	@RequestMapping("/xidou")
	public String index2() {
		return PREFIX + "/basicinfo_xd.html";
	}
	/**
	 * 跳转到定制商品列表页面
	 */
	@RequestMapping("/dz")
	public String index3() {
		return PREFIX + "/basicinfo_dz.html";
	}

	/**
	 * 获取普通商品列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String name) {
		Page<Basicinfo> page = new PageFactory<Basicinfo>().defaultPage();

		List<Map<String, Object>> list = this.basicinfoService.selectBasicinfos(page,super.getPara("name"),page.getOrderByField(), page.isAsc());
		for (Map<String, Object> m : list) {
			// 查询分类名称
			Integer categoryId = (Integer) m.get("categoryId");
			String categoryName = this.categoryService.selectById(categoryId).getPname()+"-"+this.categoryService.selectById(categoryId).getName();
			m.put("categoryName", categoryName);
			// 查询是否有封面图
			Integer basicinfoId = (Integer) m.get("id");
			String coverPics = this.basicinfoService.selectCoverPic(basicinfoId);
			if (coverPics != null && !"".equals(coverPics)) {
				m.put("pic", coverPics);
			}
		}
		//return super.warpObject(new BasicinfoWapper(list));
		return super.packForBT(page.setRecords((List<Basicinfo>)  new BasicinfoWapper(list).warp()));
	}
	/**
	 * 获取喜豆商品列表
	 */
	@RequestMapping(value = "/list_xd")
	@ResponseBody
	public Object listXd(@RequestParam(required = false) String name) {
		List<Map<String, Object>> list = this.basicinfoService.selectBasicinfoXds(super.getPara("name"));
		for (Map<String, Object> m : list) {
			// 查询分类名称
			Integer categoryId = (Integer) m.get("categoryId");
			String categoryName = this.categoryService.selectById(categoryId).getPname()+"-"+this.categoryService.selectById(categoryId).getName();
			m.put("categoryName", categoryName);
			// 查询是否有封面图
			Integer basicinfoId = (Integer) m.get("id");
			String coverPics = this.basicinfoService.selectCoverPic(basicinfoId);
			if (coverPics != null && !"".equals(coverPics)) {
				m.put("pic", coverPics);
			}
		}
		return super.warpObject(new BasicinfoWapper(list));
	}
	/**
	 * 获取定制商品列表
	 */
	@RequestMapping(value = "/list_dz")
	@ResponseBody
	public Object listDz(@RequestParam(required = false) String name) {
		List<Map<String, Object>> list = this.basicinfoService.selectBasicinfoDzs(super.getPara("name"));
		for (Map<String, Object> m : list) {
			// 查询分类名称
			Integer categoryId = (Integer) m.get("categoryId");
			String categoryName = this.categoryService.selectById(categoryId).getPname()+"-"+this.categoryService.selectById(categoryId).getName();
			m.put("categoryName", categoryName);
			// 查询是否有封面图
			Integer basicinfoId = (Integer) m.get("id");
			String coverPics = this.basicinfoService.selectCoverPic(basicinfoId);
			if (coverPics != null && !"".equals(coverPics)) {
				m.put("pic", coverPics);
			}
		}
		return super.warpObject(new BasicinfoWapper(list));
	}
	/**
	 * 转到添加页面-品牌商品
	 */
	@RequestMapping(value = "/basicinfo_add")
	public String basicinfoAdd(Model model) {
		List<Map<String, Object>> categorys = this.basicinfoService.selectCategory();
		model.addAttribute("categorys", categorys);
		return PREFIX + "/basicinfo_add.html";
	}
	/**
	 * 转到添加页面-品牌定制
	 */
	@RequestMapping(value = "/basicinfo_add_dz")
	public String basicinfoAddDz(Model model) {
		List<Map<String, Object>> categorys = this.basicinfoService.selectCategoryDz();
		model.addAttribute("categorys", categorys);
		return PREFIX + "/basicinfo_add_dz.html";
	}
	/**
	 * 转到添加页面-喜豆商品
	 */
	@RequestMapping(value = "/basicinfo_add_xd")
	public String basicinfoAddXd(Model model) {
		List<Map<String, Object>> categorys = this.basicinfoService.selectCategoryXd();
		model.addAttribute("categorys", categorys);
		return PREFIX + "/basicinfo_add_xd.html";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Tip add(@Valid Basicinfo basicinfo, BindingResult result, @RequestParam(required = false) String picName) {
		basicinfo.setDateAdd(new Date());
		basicinfo.setAddUser(ShiroKit.getUser().getName());
		// banner.setPic(picName);
//		if(basicinfo.getXidou()!=null&&basicinfo.getXidou()>0) {
//			basicinfo.setType("2");
//		}else {
//			basicinfo.setXidou(0);
//			basicinfo.setType("1");
//		}
		this.basicinfoService.insert(basicinfo);
		return SUCCESS_TIP;
	}
	/**
	 * 跳转到修改页面-品牌定制商品
	 */
	@RequestMapping(value = "/basicinfo_edit_dz/{basicinfoId}")
	public String categoryEditDz(@PathVariable Integer basicinfoId, Model model) {
		if (ToolUtil.isEmpty(basicinfoId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Basicinfo basicinfo = this.basicinfoService.selectById(basicinfoId);
		DecimalFormat df=new DecimalFormat("0.00");
		String min =df.format(basicinfo.getMinPrice());
		String old =df.format(basicinfo.getOriginalPrice());
		model.addAttribute(basicinfo);
		List<Map<String, Object>> categorys = this.basicinfoService.selectCategoryDz();
		model.addAttribute("originalPriceStr", old);
		model.addAttribute("minPriceStr", min);
		model.addAttribute("categorys", categorys);
		return PREFIX + "/basicinfo_edit_dz.html";
	}
	/**
	 * 跳转到修改页面-品牌商品
	 */
	@RequestMapping(value = "/basicinfo_edit/{basicinfoId}")
	public String categoryEdit(@PathVariable Integer basicinfoId, Model model) {
		if (ToolUtil.isEmpty(basicinfoId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Basicinfo basicinfo = this.basicinfoService.selectById(basicinfoId);
		DecimalFormat df=new DecimalFormat("0.00");
		String min =df.format(basicinfo.getMinPrice());
		String old =df.format(basicinfo.getOriginalPrice());
		model.addAttribute(basicinfo);
		List<Map<String, Object>> categorys = this.basicinfoService.selectCategory();
		model.addAttribute("originalPriceStr", old);
		model.addAttribute("minPriceStr", min);
		model.addAttribute("categorys", categorys);
		return PREFIX + "/basicinfo_edit.html";
	}
	/**
	 * 跳转到修改页面-喜豆商品
	 */
	@RequestMapping(value = "/basicinfo_edit_xd/{basicinfoId}")
	public String categoryEditXd(@PathVariable Integer basicinfoId, Model model) {
		if (ToolUtil.isEmpty(basicinfoId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Basicinfo basicinfo = this.basicinfoService.selectById(basicinfoId);
		DecimalFormat df=new DecimalFormat("0.00");
		String old =df.format(basicinfo.getOriginalPrice());
		model.addAttribute(basicinfo);
		model.addAttribute("originalPriceStr", old);
		List<Map<String, Object>> categorys = this.basicinfoService.selectCategoryXd();
		model.addAttribute("categorys", categorys);
		return PREFIX + "/basicinfo_edit_xd.html";
	}
	/**
	 * 修改
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Tip edit(@Valid Basicinfo basicinfo, BindingResult result, @RequestParam(required = false) String picName,
			@RequestParam(required = false) Boolean picEdit) {
		if (result.hasErrors()) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}

		basicinfo.setDateUpdate(new Date());
		basicinfo.setUpdateUser(ShiroKit.getUser().getName());
		this.basicinfoService.updateById(basicinfo);

		// 删除缓存
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
	}

	/**
	 * 删除商品
	 */
	@RequestMapping(value = "/remove")
	@ResponseBody
	public Tip remove(@RequestParam Integer basicinfoId) {
		if (ToolUtil.isEmpty(basicinfoId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		/*
		 * 逻辑删除代码 Basicinfo basicinfo = this.basicinfoService.selectById(basicinfoId);
		 * basicinfo.setDateUpdate(new Date());
		 * basicinfo.setUpdateUser(ShiroKit.getUser().getName());
		 * basicinfo.setStatus(3); this.basicinfoService.deleteBasicinfo(basicinfo);
		 */
		this.basicinfoService.deleteBasicinfoById(basicinfoId);

		// 删除缓存
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
	}

	/**
	 * 跳转到图片页面
	 */
	@RequestMapping(value = "/basicinfo_edit_pic/{basicinfoId}")
	public String editPic(@PathVariable Integer basicinfoId, Model model) {
		if (ToolUtil.isEmpty(basicinfoId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Basicinfo basicinfo = this.basicinfoService.selectById(basicinfoId);
		model.addAttribute(basicinfo);
		return PREFIX + "/basicinfo_pic.html";
	}
	/**
	 * 跳转到数量配置页面
	 */
	@RequestMapping(value = "/num_spec/{basicinfoId}")
	public String numSpec(@PathVariable Integer basicinfoId, Model model) {
		if (ToolUtil.isEmpty(basicinfoId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Basicinfo basicinfo = this.basicinfoService.selectById(basicinfoId);
		model.addAttribute(basicinfo);
		return PREFIX + "/basicinfo_num_spec.html";
	}
	/**
	 * 获取定制商品数量配置列表
	 */
	@RequestMapping(value = "/num_spec_list/{bid}")
	@ResponseBody
	public Object listNumSpec(@PathVariable Integer bid) {
		
		List<Map<String, Object>> list = this.basicinfoService.selectBascinfoNumSpec(bid);
		for(Map<String, Object> m :list) {
			Integer begin = (Integer)m.get("begin");
			Integer end = (Integer)m.get("end");
			String fanwei = begin+"-"+end;
			m.put("scope", fanwei);
		}
		return list;
	}
	/**
	 * 添加数量配置
	 */
	@RequestMapping(value = "/add_num_spec")
	@ResponseBody
	public Tip addNumSpec(@Valid NumSpec numSpec, BindingResult result) {
		Integer userid = ShiroKit.getUser().getId();
		numSpec.setUserId(userid);
		numSpec.setCreatetime(new Date());
		this.basicinfoService.addNumspec(numSpec);
	
		return SUCCESS_TIP;
	}
	/**
	 * 修改数量配置
	 */
	@RequestMapping(value = "/edit_num_spec")
	@ResponseBody
	public Tip editNumSpec(@Valid NumSpec numSpec, BindingResult result) {
//		if (result.hasErrors()) {
//			throw new KingException(BizExceptionEnum.REQUEST_NULL);
//		}
		numSpec.setUserId(ShiroKit.getUser().getId());
		numSpec.setCreatetime(new Date());
		this.basicinfoService.updateNumspec(numSpec);

		// 删除缓存
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
	}
	/**
	 * 删除商品数量配置
	 */
	@RequestMapping(value = "/remove_num_spec")
	@ResponseBody
	public Tip removeNumSpec(@RequestParam Integer id) {
		if (ToolUtil.isEmpty(id)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		
		this.basicinfoService.deleteNumspecById(id);

		// 删除缓存
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
	}
	/**
	 * 获取图片列表
	 */

	@RequestMapping(value = "/pic_list/{bid}")
	@ResponseBody
	public Object piclist(@PathVariable Integer bid) {
		List<Map<String, Object>> pic = this.basicinfoService.selectPicByBasicinfoId(bid);
		for (Map<String, Object> map : pic) {
			map.put("picTypeStr", ConstantFactory.me().getDictPicTypeName((Integer) map.get("type")));
		}
		return pic;
	}
	/**
	 * 转到数量配置添加页面 
	 */
	@RequestMapping(value = "/num_spec_add/{basicinfoId}")
	public String numSpecAdd(@PathVariable Integer basicinfoId, Model model) {
		model.addAttribute("basicinfoId", basicinfoId);
		return PREFIX + "/basicinfo_num_spec_add.html";
	}
	/**
	 * 转到数量配置修改页面 
	 */
	@RequestMapping(value = "/num_spec_edit/{id}")
	public String numSpecEdit(@PathVariable Integer id, Model model) {
		if (ToolUtil.isEmpty(id)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		NumSpec ns = this.basicinfoService.selectNumSpecByid(id);
		model.addAttribute("ns",ns);
		return PREFIX + "/basicinfo_num_spec_edit.html";
	}
	/**
	 * 转到图片添加页面 图片列表入口
	 */
	@RequestMapping(value = "/pic_add/{basicinfoId}")
	public String picAdd(@PathVariable Integer basicinfoId, Model model) {
		model.addAttribute("basicinfoId", basicinfoId);
		return PREFIX + "/basicinfo_pic_add.html";
	}

	/**
	 * 转到图片添加页面 商品列表入口
	 */
	@RequestMapping(value = "/pic_add2/{basicinfoId}")
	public String picAdd2(@PathVariable Integer basicinfoId, Model model) {
		model.addAttribute("basicinfoId", basicinfoId);
		return PREFIX + "/basicinfo_pic_add2.html";
	}

	/**
	 * 添加 图片列表入口
	 */
	@RequestMapping(value = "/picadd")
	@ResponseBody
	public Tip picadd(@Valid Picture picture, BindingResult result, @RequestParam(required = false) String picName) {
		picture.setType(1);
		picture.setStatus(1);
		picture.setDateAdd(new Date());
		picture.setAddUser(ShiroKit.getUser().getName());
		picture.setPic(picName);
		this.basicinfoService.insertPic(picture);
		return SUCCESS_TIP;
	}

	/**
	 * 添加 商品列表入口
	 */
	@RequestMapping(value = "/picadd2")
	@ResponseBody
	public Tip picadd2(@Valid Basicinfo basicinfo, BindingResult result,
			@RequestParam(required = false) String picName) {
		Picture picture = new Picture();
		picture.setBasicinfoId(basicinfo.getId());
		picture.setType(1);
		picture.setStatus(1);
		picture.setDateAdd(new Date());
		picture.setAddUser(ShiroKit.getUser().getName());
		picture.setPic(picName);
		this.basicinfoService.insertPic(picture);
		return SUCCESS_TIP;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/pic_remove")
	@ResponseBody
	public Tip picRemove(@RequestParam Integer picId) {
		if (ToolUtil.isEmpty(picId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		/*
		 * 逻辑删除图片 Picture picture = new Picture(); picture.setId(picId);
		 * picture.setStatus(3); picture.setDateUpdate(new Date());
		 * picture.setUpdateUser(ShiroKit.getUser().getName());
		 * this.basicinfoService.updatePic(picture);
		 */
		this.basicinfoService.deletePicById(picId);// 删除
		// 图片操作记录
		// this.operationService.insertBizOperation(picture.getPic(), "",
		// "BasicinfoController+picRemove+picId="+picId, ShiroKit.getUser().getName());
		// 删除缓存
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
	}

	/**
	 * 设置封面
	 */
	@RequestMapping(value = "/cover")
	@ResponseBody
	public Tip cover(@RequestParam Integer picId, @RequestParam Integer basicinfoId) {
		if (ToolUtil.isEmpty(picId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		// 先取消其他封面
		this.basicinfoService.updateCoverPic(basicinfoId, ShiroKit.getUser().getName());
		Picture picture = new Picture();
		picture.setId(picId);
		picture.setStatus(1);
		picture.setType(2);
		picture.setDateUpdate(new Date());
		picture.setUpdateUser(ShiroKit.getUser().getName());
		this.basicinfoService.updatePic(picture);

		return SUCCESS_TIP;
	}

	/**
	 * 取消封面
	 */
	@RequestMapping(value = "/cancel_cover")
	@ResponseBody
	public Tip cancelCover(@RequestParam Integer picId) {
		if (ToolUtil.isEmpty(picId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Picture picture = new Picture();
		picture.setId(picId);
		picture.setStatus(1);
		picture.setType(1);
		picture.setDateUpdate(new Date());
		picture.setUpdateUser(ShiroKit.getUser().getName());
		this.basicinfoService.updatePic(picture);

		return SUCCESS_TIP;
	}

	/**
	 * 跳转到规格分配
	 */
	@RequestMapping(value = "/basicinfo_assign/{basicinfoId}")
	public String roleAssign(@PathVariable("basicinfoId") Integer basicinfoId, Model model) {
		if (ToolUtil.isEmpty(basicinfoId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Basicinfo basicinfo = this.basicinfoService.selectById(basicinfoId);
		model.addAttribute("basicinfoId", basicinfoId);
		model.addAttribute("basicinfoName", basicinfo.getName());
		return PREFIX + "/basicinfo_assign.html";
	}

	/**
	 * 获取商品规格列表
	 */
	@RequestMapping(value = "/specTreeListBybasicinfoId/{basicinfoId}")
	@ResponseBody
	public List<ZTreeNode> specTreeListBybasicinfoId(@PathVariable Integer basicinfoId) {
		List<Long> menuIds = this.basicinfoService.getSpecIdsByBasicinfoId(basicinfoId);
		if (ToolUtil.isEmpty(menuIds)) {
			List<ZTreeNode> roleTreeList = this.basicinfoService.specTreeList();
			return roleTreeList;
		} else {
			List<ZTreeNode> roleTreeListByUserId = this.basicinfoService.specTreeListBySpecIds(menuIds);
			return roleTreeListByUserId;
		}
	}

	/**
	 * 配置规格
	 */
	@RequestMapping("/setSpec")
	@ResponseBody
	public Tip setAuthority(@RequestParam("basicinfoId") Integer basicinfoId, @RequestParam("ids") String ids) {
		if (ToolUtil.isOneEmpty(basicinfoId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		this.basicinfoService.setSpec(basicinfoId, ids);
		return SUCCESS_TIP;
	}
	
	/**
	 * 设置推荐
	 */
	@RequestMapping(value = "/recommend")
	@ResponseBody
	public Tip recommend(@RequestParam Integer id) {
		if (ToolUtil.isEmpty(id)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Basicinfo basicinfo = this.basicinfoService.selectById(id);
		basicinfo.setDateUpdate(new Date());
		basicinfo.setUpdateUser(ShiroKit.getUser().getName());
		basicinfo.setRecommend(1);
		this.basicinfoService.updateById(basicinfo);	
		return SUCCESS_TIP;
	}


}

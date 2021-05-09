package com.kingoin.king.modular.biz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.kingoin.king.core.base.controller.BaseController;
import com.kingoin.king.core.common.constant.factory.ConstantFactory;
import com.kingoin.king.core.common.exception.BizExceptionEnum;
import com.kingoin.king.core.exception.KingException;
import com.kingoin.king.core.log.LogObjectHolder;
import com.kingoin.king.core.util.ToolUtil;
import com.kingoin.king.modular.biz.model.Specification;
import com.kingoin.king.modular.biz.service.ISpecService;
import com.kingoin.king.modular.biz.warpper.SpecWarpper;

/**
 * <p>
 * 规格控制器
 * </p>
 * 
 * @author serson
 * @date 2019年11月25日
 * @time 上午11:47:06
 */
@Controller
@RequestMapping("/spec")
public class SpecController extends BaseController {

	private String PREFIX = "/biz/spec/";

	@Autowired
	private ISpecService specService;

	/**
	 * 跳转到规格管理首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "spec.html";
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String condition) {
		List<Map<String, Object>> list = this.specService.list(condition);
		return super.warpObject(new SpecWarpper(list));
	}

	/**
	 * 跳转到添加页面
	 */
	@RequestMapping("/spec_add")
	public String specAdd(Model model) {
		/*
		 * 
		 * model.addAttribute("typeNames",list.get(0)); }else { Map<String, Object> map
		 * = new HashMap<String, Object>(); map.put("name", ""); list.add(map);
		 * model.addAttribute("typeNames",list); }
		 */

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = this.specService.selectSpecType("规格类别");
		model.addAttribute("list", list);
		return PREFIX + "spec_add.html";
	}

	/**
	 * 新增
	 *
	 * @param dictValues 格式例如 "1:启用;2:禁用;3:冻结"
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(String specName, String typeName, String specValues) {
		if (ToolUtil.isOneEmpty(specName, specValues)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		this.specService.addSpec(specName, typeName, specValues);
		return SUCCESS_TIP;
	}

	/**
	 * 跳转到修改页面
	 */
	@RequestMapping("/spec_edit/{specId}")
	public String deptUpdate(@PathVariable Integer specId, Model model) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = this.specService.selectSpecType("规格类别");
		model.addAttribute("list", list);
		Specification dict = specService.selectById(specId);
		model.addAttribute("dict", dict);
		List<Specification> subDicts = specService.selectList(new EntityWrapper<Specification>().eq("pid", specId));
		model.addAttribute("subDicts", subDicts);
		LogObjectHolder.me().set(dict);
		return PREFIX + "spec_edit.html";
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Integer dictId, String dictName, String typeName, String dictValues) {
		if (ToolUtil.isOneEmpty(dictId, dictName, dictValues)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		specService.editSpec(dictId, typeName, dictName, dictValues);
		return SUCCESS_TIP;
	}

	/**
	 * 删除记录
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam("specId") Integer specId) {

		// 缓存被删除的名称
		LogObjectHolder.me().set(ConstantFactory.me().getSpecName(specId));

		this.specService.delteSpec(specId);
		return SUCCESS_TIP;
	}
}

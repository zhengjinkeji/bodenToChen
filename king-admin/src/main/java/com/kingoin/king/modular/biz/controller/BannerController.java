package com.kingoin.king.modular.biz.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kingoin.king.config.properties.KingProperties;
import com.kingoin.king.core.base.controller.BaseController;
import com.kingoin.king.core.base.tips.Tip;
import com.kingoin.king.core.cache.CacheKit;
import com.kingoin.king.core.common.constant.cache.Cache;
import com.kingoin.king.core.common.exception.BizExceptionEnum;
import com.kingoin.king.core.exception.KingException;
import com.kingoin.king.core.shiro.ShiroKit;
import com.kingoin.king.core.util.ToolUtil;
import com.kingoin.king.modular.biz.model.Banner;
import com.kingoin.king.modular.biz.service.IBannerService;
import com.kingoin.king.modular.biz.warpper.BannerWarpper;

/**
 * <p>
 * 轮播控制器
 * </p>
 * 
 * @author serson
 * @date 2019年11月16日
 * @time 下午10:04:45
 */
@Controller
@RequestMapping("/banner")
public class BannerController extends BaseController {

	private static String PREFIX = "/biz/banner";

	@Autowired
	private IBannerService bannerService;

	@Autowired
	private KingProperties kingProperties;

	/**
	 * 跳转到轮播列表页面
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "/banner.html";
	}

	/**
	 * 获取banner列表
	 */

	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String title) {
		List<Map<String, Object>> list = this.bannerService.selectBanners(super.getPara("title"));
		return super.warpObject(new BannerWarpper(list));
	}

	/**
	 * 转到banner添加
	 */
	@RequestMapping(value = "/banner_add")
	public String bannerAdd() {
		return PREFIX + "/banner_add.html";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Tip add(@Valid Banner banner, BindingResult result, @RequestParam(required = false) String picName) {

		banner.setDateAdd(new Date());
		banner.setAddUser(ShiroKit.getUser().getName());
		banner.setPic(picName);
		this.bannerService.insert(banner);
		return SUCCESS_TIP;
	}

	/**
	 * 上传图片
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String fileName) {
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    	String date = sdf.format(new Date());
		String pictureName = UUID.randomUUID().toString() + ".jpg"; // +"-" +name;
		//String fileUploadPath = "D:\\tool\\tomcat8.5\\apache-tomcat-8.5.47\\caixcx\\image\\" + fileName;
		//String fileUploadPath = "C:\\tom\\apache-tomcat-8.5.47\\app\\image\\" + fileName;
		String fileUploadPath = "B:\\tomcat\\apache-tomcat-8.5.47-8081\\xcx\\cai_images\\"+ fileName;
		try {
			kingProperties.setFileUploadPath(fileUploadPath);
			String fileSavePath = kingProperties.getFileUploadPath();
			file.transferTo(new File(fileSavePath + pictureName));
		} catch (Exception e) {
			e.printStackTrace();
			throw new KingException(BizExceptionEnum.UPLOAD_ERROR);
		}
		return pictureName;
	}

	/**
	 * 跳转到修改页面
	 */
	@RequestMapping(value = "/banner_edit/{bannerId}")
	public String bannerEdit(@PathVariable Integer bannerId, Model model) {
		if (ToolUtil.isEmpty(bannerId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Banner banner = this.bannerService.selectById(bannerId);
		model.addAttribute(banner);
		return PREFIX + "/banner_edit.html";
	}

	/**
	 * banner修改
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Tip edit(@Valid Banner banner, BindingResult result, @RequestParam(required = false) String picName,
			@RequestParam(required = false) Boolean picEdit) {
		if (result.hasErrors()) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		if (picEdit && picName != null && !"".equals(picName)) {// 修改图片
			banner.setPic(picName);
			//Banner b = this.bannerService.selectById(banner.getId());

			banner.setDateUpdate(new Date());
			banner.setUpdateUser(ShiroKit.getUser().getName());
			this.bannerService.updateById(banner);
			// 图片操作记录
			// this.operationService.insertBizOperation(b.getPic(), picName,
			// "BannerController+edit+bannerId="+banner.getId(),
			// ShiroKit.getUser().getName());

		} else {

			banner.setDateUpdate(new Date());
			banner.setUpdateUser(ShiroKit.getUser().getName());
			this.bannerService.updateById(banner);
		}

		// 删除缓存
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
	}

	/**
	 * 跳转到更新图片页面
	 */
	@RequestMapping(value = "/banner_edit_pic/{bannerId}")
	public String editPic(@PathVariable Integer bannerId, Model model) {
		if (ToolUtil.isEmpty(bannerId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Banner banner = this.bannerService.selectById(bannerId);
		model.addAttribute(banner);
		return PREFIX + "/banner_edit_pic.html";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/remove")
	@ResponseBody
	public Tip remove(@RequestParam Integer bannerId) {
		if (ToolUtil.isEmpty(bannerId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		/**
		 * 逻辑删除 Banner banner = this.bannerService.selectById(bannerId);
		 * banner.setDateUpdate(new Date());
		 * banner.setUpdateUser(ShiroKit.getUser().getName()); banner.setStatus(3);
		 * banner.setStatusStr("删除"); this.bannerService.updateById(banner);
		 */
		this.bannerService.deleteById(bannerId);

		// 删除缓存
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
	}
	
}

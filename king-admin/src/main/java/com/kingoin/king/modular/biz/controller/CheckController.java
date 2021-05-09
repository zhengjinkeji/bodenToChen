package com.kingoin.king.modular.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingoin.king.core.base.controller.BaseController;
import com.kingoin.king.core.base.tips.Tip;
import com.kingoin.king.core.cache.CacheKit;
import com.kingoin.king.core.common.annotion.BussinessLog;
import com.kingoin.king.core.common.annotion.Permission;
import com.kingoin.king.core.common.constant.Const;
import com.kingoin.king.core.common.constant.cache.Cache;
import com.kingoin.king.core.common.constant.dictmap.UserDict;
import com.kingoin.king.core.common.constant.state.ManagerStatus;
import com.kingoin.king.core.common.exception.BizExceptionEnum;
import com.kingoin.king.core.exception.KingException;
import com.kingoin.king.core.shiro.ShiroKit;
import com.kingoin.king.core.util.ToolUtil;
import com.kingoin.king.modular.biz.service.ICheckService;
import com.kingoin.king.modular.system.model.User;
import com.kingoin.king.modular.system.service.IUserService;

/**
 * <p>
 *	待审核用户控制器
 * </p>
 * @author serson
 * @date 2019年12月27日
 * @time 下午6:37:00
 */
@Controller
@RequestMapping("/check")
public class CheckController extends BaseController{

	private static String PREFIX = "/biz/check";
	
	@Autowired
	private ICheckService checkService;
	
	@Autowired
    private IUserService userService;
	
	/**
	 * 跳转到待审核用户页面
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "/check.html";
	}
	
	/**
	 * 获取用户列表
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String title) {
		List<Map<String, Object>> list = this.checkService.selectRegisterUser(title);
		for(Map<String, Object> m:list) {
			String account = (String)m.get("account");
			List<Map<String, Object>> userInfo = this.checkService.selectRegisterUserInfo(account);
			for(Map<String, Object> info:userInfo) {
				String picType = (String) info.get("pic_type");
				String pic = (String) info.get("pic");
				m.put(picType, pic);
			}
			m.put("statusStr", "待审核");
		}
		return list;
	}
	/**
	 * 跳转到查看图片页面  ---暂不启用
	 */
	@RequestMapping(value = "/checkPic/{account}")
	public String editPic(@PathVariable String account, Model model) {
		if (ToolUtil.isEmpty(account)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		List<Map<String, Object>> userInfo = this.checkService.selectRegisterUserInfo(account);
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i=0;i<userInfo.size();i++) {
			Map<String, Object> m = userInfo.get(i);
			map.put((String)m.get("pic_type"), (String)m.get("pic"));
		}
		model.addAttribute("map", map);
		return PREFIX + "/check_pic.html";
	}
	
	/**
	 * 通过
	 */
	@RequestMapping(value = "/pass")
	@ResponseBody
	public Tip pass(@RequestParam Integer userId) {
		if (ToolUtil.isEmpty(userId)) {
            throw new KingException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.OK.getCode());
        return SUCCESS_TIP;
	}
	 /**
	  * 删除（逻辑删除）
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Tip delete(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new KingException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能删除超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new KingException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }
        assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.DELETED.getCode());
        return SUCCESS_TIP;
    }

	 /**
     * 判断当前登录的用户是否有操作这个用户的权限
     */
    private void assertAuth(Integer userId) {
        if (ShiroKit.isAdmin()) {
            return;
        }
        List<Integer> deptDataScope = ShiroKit.getDeptDataScope();
        User user = this.userService.selectById(userId);
        Integer deptid = user.getDeptid();
        if (deptDataScope.contains(deptid)) {
            return;
        } else {
            throw new KingException(BizExceptionEnum.NO_PERMITION);
        }

    }

	/**
	 * 获取资料列表主页
	 */
	@RequestMapping(value = "/index")
	public String mobile (Model model) {
		//return "/mobile/index.html";
		return PREFIX+"/index.html";
	}
	/**
	 * 获取资料列表
	 */
	@RequestMapping(value = "/queryMeterialListHtml")
	public String queryMeterialListHtml (Model model,@RequestParam("id")Integer id,@RequestParam("id")Integer type) {
		model.addAttribute("id",id);
		model.addAttribute("type",type);
		return PREFIX+"/meterial_list.html";
	}
	/**
	 * 获取喜豆商品列表
	 */
	@RequestMapping(value = "/gzhRegister")
	public Object gzhRegister (Model model,@RequestParam("mobilePhone")String mobilePhone) {
		//return "/mobile/index.html";
		model.addAttribute("mobilePhone",mobilePhone);
		return PREFIX+"/gzh_register.html";
	}
	/**
	 * 扫描设备页面
	 */
	@RequestMapping(value = "/scanQrCode")
	public Object scanQrCode (Model model) {
		return PREFIX+"/scan_qrcode.html";
	}
	/**
	 * 进入公众号页面
	 */
	@RequestMapping(value = "/gzhLogin")
	public String gzhLogin (Model model) {
		//return "/mobile/index.html";
		return PREFIX+"/gzh_login.html";
	}

	/**
	 * 进入公众号页面
	 */
	@RequestMapping(value = "/pallet")
	public String pallet (Model model) {
		//return "/mobile/index.html";
		return PREFIX+"/pallet.html";
	}
	
}

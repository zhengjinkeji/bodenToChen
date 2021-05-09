package com.kingoin.king.modular.biz.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;

import com.kingoin.king.core.common.constant.factory.ConstantFactory;
import com.kingoin.king.modular.biz.dto.QueryUserListDTO;
import com.kingoin.king.modular.biz.service.UploadFileService;
import com.kingoin.king.modular.biz.util.SysUtils;
import com.kingoin.king.modular.system.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kingoin.king.config.properties.KingProperties;
import com.kingoin.king.core.shiro.ShiroKit;
import com.kingoin.king.core.util.ToolUtil;
import com.kingoin.king.modular.biz.dto.Result;
import com.kingoin.king.modular.biz.dto.UserInfo;
import com.kingoin.king.modular.biz.model.Order;
import com.kingoin.king.modular.biz.service.IAppService;
import com.kingoin.king.modular.biz.service.IOrderService;
import com.kingoin.king.modular.system.factory.UserFactory;
import com.kingoin.king.modular.system.model.Notice;
import com.kingoin.king.modular.system.model.User;
import com.kingoin.king.modular.system.service.INoticeService;
import com.kingoin.king.modular.system.service.IUserService;
import com.kingoin.king.modular.system.transfer.UserDto;

/**
 * <p>
 * 小程序后台
 * </p>
 * 
 * @author serson
 * @date 2019年11月28日
 * @time 上午10:29:06
 */
@Controller
@RequestMapping("/app")
public class AppController {

	@Autowired
	private IAppService appService;

	@Autowired
	private IUserService userService;

	@Autowired
	private KingProperties kingProperties;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private INoticeService noticeService;

	@Autowired
	private UploadFileService uploadFileService;

	@Autowired
	private UserMapper userMapper;

	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	/**
	 * 启动和首页
	 *
	 * @param type
	 * @return
	 */
	@RequestMapping("/banner/list")
	@ResponseBody
	public Object banner(@RequestParam(required = false) String type) {
		Result re = new Result();
		if (ToolUtil.isEmpty(type)) {
			re.setCode(500);
			re.setMessage("服务器异常");
			return re;
		}
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
			Map<String, Object> rceommend = new HashMap<String, Object>();
			//String url = "http://localhost:8080/image/banner/";
			String url = "https://www.caiufh.com/cai_images/banner/";
			//String url = "http://111.229.181.74:8081/image/banner/";
			switch (type) {// 判断banner请求类型 start-启动 index -首页
				case "start":
					list = this.appService.selectStartPics();// 获取启动信息

					for (Map<String, Object> m : list) {
						String picName = (String) m.get("pic");
						String picUrl = url + picName;
						m.put("picUrl", picUrl);
					}
					break;
				case "index":
					list = this.appService.selectIndexBanners();// 查询首页轮播图
					for (Map<String, Object> m : list) {
						String picName = (String) m.get("pic");
						String picUrl = url + picName;
						m.put("picUrl", picUrl);
					}
					Map<String, Object> map1 = new HashMap<String, Object>();
					Map<String, Object> map2 = new HashMap<String, Object>();
//				map1.put("picUrl", "http://localhost:8080/image/t1.png");
//				map2.put("picUrl", "http://localhost:8080/image/t2.png");
//				map1.put("picUrl", "https://www.wscyun.cn/image/t1.png");
//				map2.put("picUrl", "https://www.wscyun.cn/image/t2.png");
					map1.put("picUrl", "https://www.caiufh.com/cai_images/t1.png");
					map2.put("picUrl", "https://www.caiufh.com/cai_images/t2.png");
					list2.add(map1);
					list2.add(map2);
					list3 = this.appService.selectRceommend();
					for (Map<String, Object> m : list3) {
						// 查询是否有封面图
						Integer basicinfoId = (Integer) m.get("id");
						String coverPics = this.appService.selectCoverPic(basicinfoId);
						if (coverPics != null && !"".equals(coverPics)) {
							m.put("pic", "https://www.caiufh.com/cai_images/basicinfo/" + coverPics);
						}
					}
					break;
				case "cate":
					list = this.appService.selectCateBanners();// 查询分类轮播图
					for (Map<String, Object> m : list) {
						String picName = (String) m.get("pic");
						String picUrl = url + picName;
						m.put("picUrl", picUrl);
					}

					break;
				default:
					break;
			}
			if (list != null && list.size() != 0) {
				re.setList(list);
			}
			if (list2 != null && list2.size() != 0) {
				re.setList2(list2);
			}
			if (list3 != null && list3.size() != 0) {
				rceommend.put("goods", list3);
				rceommend.put("value", "人气爆款");
				re.setMap(rceommend);
			}

			re.setHasMore(true);
			re.setCode(0);
			re.setMessage("OK");
			return re;

		} catch (Exception e) {
			e.printStackTrace();
			re.setCode(500);
			re.setMessage("服务器异常");
			return re;
		}
	}

	/**
	 * 注册账号验证
	 *
	 * @return
	 */
	@RequestMapping(value = "/register/register")
	@ResponseBody
	public Object register(@RequestParam("company") String company,
						   @RequestParam("account") String account,
						   @RequestParam("password") String password,
						   @RequestParam(value = "idCardNo", required = false) String idCardNo) {
		Result re = new Result();
		try {
			if (!ToolUtil.isEmpty(account) && !ToolUtil.isEmpty(password)) {
				User theUser = userService.getByAccount(account);
				if (theUser != null) {
					re.setCode(0);
					re.setMessage("账号已经被注册过");
					return re;
				} else {
					UserDto user = new UserDto();
					// 完善账号信息
					user.setName(company);
					user.setAccount(account);
					user.setSalt(ShiroKit.getRandomSalt(8));
					user.setPassword(ShiroKit.encrypt(password, user.getSalt()));
					//存放明文密码
					user.setEmail(password);
					user.setStatus(5);
					user.setCreatetime(new Date());
					user.setIdCardNo(idCardNo);
					this.userService.insert(UserFactory.createUser(user));
					return re;
				}
			} else {
				re.setCode(404);
				re.setMessage("系统繁忙请稍后再试");
				return re;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			SysUtils.setErrorMsg(re, e.getMessage());
			return re;
		}
	}


	/**
	 * 上传营业执照
	 *
	 * @return
	 */
	@RequestMapping(value = "/upload/uploadBusinessLicense")
	@ResponseBody
	public Object register(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
		Result re = new Result();
		try {
			String account = request.getHeader("account");
				String prefix = "rlb";
			if (!ToolUtil.isEmpty(account)) {
				User theUser = userService.getByAccount(account);
				if (theUser != null) {
					//更新用户表营业执照的url
					Result result = uploadFileService.uploadFile(file, prefix);
					if (result == null || result.getCode() == 500) {
						throw new Exception("营业执照上传失败!");
					}
					theUser.setBusinessLicenseUrl((String) result.getMap().get("path"));
					theUser.setStatus(4);
					theUser.setSubmitDate(new Date());
					userMapper.updateById(theUser);
				} else {
					throw new Exception("用户" + account + "不存在");
				}
			} else {
				re.setCode(404);
				re.setMessage("系统繁忙请稍后再试");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			SysUtils.setErrorMsg(re, e.getMessage());
		}
		return re;
	}

	/**
	 * 上传资料
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public Object upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Result re = new Result();
		String pictureName = UUID.randomUUID().toString() + ".jpg"; // +"-" +name;
		//String fileUploadPath = "D:\\tool\\tomcat8.5\\apache-tomcat-8.5.47\\caixcx\\image\\user_info\\";
		//String fileUploadPath = "C:\\tom\\apache-tomcat-8.5.47\\app\\image\\user_info\\";
		String fileUploadPath = "B:\\tomcat\\apache-tomcat-8.5.47-8081\\xcx\\cai_images\\user_info";
								
		try {
			String account = request.getHeader("account");// 获取用户
			String picType = request.getHeader("picType");// 获取图片类别
			if (!ToolUtil.isEmpty(account) && !ToolUtil.isEmpty(picType)) {
				kingProperties.setFileUploadPath(fileUploadPath);
				String fileSavePath = kingProperties.getFileUploadPath();
				file.transferTo(new File(fileSavePath + pictureName));
				UserInfo user = new UserInfo();
				user.setAccount(account);
				user.setPic(pictureName);
				user.setPicType(picType);
				this.appService.addUserInfo(user);
				re.setCode(200);
				re.setMessage("OK");
				return re;

			} else {
				re.setCode(404);
				re.setMessage("系统繁忙请稍后再试");
				return re;
			}
		} catch (Exception e) {
			e.printStackTrace();
			re.setCode(500);
			re.setMessage("服务器异常");
			return re;
		}

	}
	/**
	 * 上传支付凭证
	 */
	@RequestMapping("/upload_voucher")
	@ResponseBody
	public Object uploadVoucher(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Result re = new Result();
		String pictureName = UUID.randomUUID().toString() + ".jpg"; // +"-" +name;
		//String fileUploadPath = "D:\\tool\\tomcat8.5\\apache-tomcat-8.5.47\\caixcx\\image\\user_info\\";
		//String fileUploadPath = "C:\\tom\\apache-tomcat-8.5.47\\app\\image\\order\\";
		String fileUploadPath = "B:\\tomcat\\apache-tomcat-8.5.47-8081\\xcx\\cai_images\\order";
		try {
			
			String orderId = request.getHeader("orderId");// 获取订单ID
			if ( !ToolUtil.isEmpty(orderId)) {
				Order ord = this.orderService.selectById(orderId);
				kingProperties.setFileUploadPath(fileUploadPath);
				String fileSavePath = kingProperties.getFileUploadPath();
				file.transferTo(new File(fileSavePath + pictureName));
				ord.setVoucher(pictureName);
				ord.setStatus("4");
				this.orderService.updateById(ord);
				re.setCode(200);
				re.setMessage("OK");
				return re;

			} else {
				re.setCode(404);
				re.setMessage("系统繁忙请稍后再试");
				return re;
			}
		} catch (Exception e) {
			e.printStackTrace();
			re.setCode(500);
			re.setMessage("服务器异常");
			return re;
		}

	}
	/**
	 * 资料 列表
	 * 
	 * @return
	 */
	@RequestMapping("/ziliao/list")
	@ResponseBody
	public Object ziliao(@RequestParam(required = false) Integer type) {
		Result result = new Result();
		try {
			List<Map<String, Object>> list = this.noticeService.listByType(type);
			result.setList(list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(500);
			result.setMessage("服务器异常");
			return result;
		}
		
	}

	/**
	 *登录时检查用户状态
	 *
	 * @return
	 */
	@RequestMapping("/checkUserStatus")
	@ResponseBody
	public Object checkUserStatus(@RequestParam("account") String account) {
		Result result = new Result();
		try {
			User user = new User();
			user.setAccount(account);
			Map<String,Object> queryMap = new HashMap();
			queryMap.put("account",account);
			List list = userMapper.selectUserList(queryMap);
			if(ToolUtil.isEmpty(list)){
				throw new Exception("用户不存在!");
			}
			result.setList(list);
			return result;
		} catch (Exception e) {
			SysUtils.setErrorMsg(result);
			return result;
		}

	}

	/**
	 * 资料 详情
	 * @return
	 */
	@RequestMapping("/ziliao/detail")
	@ResponseBody
	public Object detail(@RequestParam(required = true) Integer id) {
		Result result = new Result();
		try {
			Notice notice = this.noticeService.selectById(id);
			Map<String, Object> map = new HashMap<>();
			map.put("title", notice.getTitle());
			if(notice.getType()==2) {
				List<String> list = new ArrayList<String>();
				
				String str = notice.getContent();
				String[] arr2 = str.split("src=\"");
				for(int i=1;i<arr2.length;i++) {
					String[] arr3 = arr2[i].split("\"");
					list.add(arr3[0]);
				}
				map.put("content", list);
				result.setCode(1);
				result.setMap(map);
				return result;
			}else {
				map.put("content", notice.getContent());
				result.setCode(0);
				result.setMap(map);
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(500);
			result.setMessage("服务器异常");
			return result;
		}
		
	}
	/**
	 * 注册账号验证
	 *
	 * @return
	 */
	@RequestMapping(value = "/audit/auditUserInfo")
	@ResponseBody
	public Object audit(@RequestParam("id")Integer id,@RequestParam("isPass")Integer isPass,
						@RequestParam(value="notPassReason",required = false)String notPassReason){
		Result re = new Result();
		try{
			User user = userMapper.selectById(id);
			if(ToolUtil.isEmpty(user)){
				throw new Exception("用户不存在");
			}
			if(isPass==1){
				user.setStatus(1);
			}else{
				//审核不通过 状态变为待补充
				user.setStatus(5);
				user.setNotPassReason(notPassReason);
			}
			user.setAuditDate(new Date());
			userMapper.updateById(user);
		}catch(Exception e){
			SysUtils.setErrorMsg(re,e.getMessage());
		}
		return re;
	}

	/**
	 * 注册账号验证
	 *
	 * @return
	 */
	@PostMapping(value = "/user/queryUserList")
	@ResponseBody
	public Object queryUserList(@RequestBody QueryUserListDTO userDto){
		Result re = new Result();
		try{
			Map<String,Object> queryMap = new HashMap<>();
			queryMap.put("id",userDto.getId());
			queryMap.put("account",userDto.getAccount());
			queryMap.put("status",userDto.getStatus());
			queryMap.put("name",userDto.getName());
			List<Map<String,Object>> userMapList = userMapper.selectUserList(queryMap);
			re.setList(userMapList);
		}catch(Exception e){
			SysUtils.setErrorMsg(re,e.getMessage());
		}
		return re;
	}
}

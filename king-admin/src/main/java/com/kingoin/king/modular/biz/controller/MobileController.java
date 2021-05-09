package com.kingoin.king.modular.biz.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.kingoin.king.core.base.controller.BaseController;
import com.kingoin.king.core.common.constant.factory.ConstantFactory;
import com.kingoin.king.core.util.ToolUtil;
import com.kingoin.king.modular.biz.dao.GzhUserMapper;
import com.kingoin.king.modular.biz.dao.MeterialMapper;
import com.kingoin.king.modular.biz.dao.MeterialTypeMapper;
import com.kingoin.king.modular.biz.dao.mapping.MeterialDetailMapper;
import com.kingoin.king.modular.biz.dto.*;
import com.kingoin.king.modular.biz.model.GzhUser;
import com.kingoin.king.modular.biz.model.Meterial;
import com.kingoin.king.modular.biz.model.MeterialDetail;
import com.kingoin.king.modular.biz.model.MeterialType;
import com.kingoin.king.modular.biz.util.SysUtils;
import com.kingoin.king.modular.system.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

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
@RequestMapping("/mobile")
@CrossOrigin
public class MobileController extends BaseController {

	@Autowired
	private MeterialMapper meterialMapper;

	@Autowired
	private MeterialDetailMapper meterialDetailMapper;

	@Autowired
	private GzhUserMapper gzhUserMapper;

	private static final Logger logger = LoggerFactory.getLogger(MobileController.class);

	@Autowired
	private MeterialTypeMapper meterialTypeMapper;
	/**
	 * 获取喜豆商品列表
	 */
	@RequestMapping(value = "/index")
	@ResponseBody
	public Object mobile (Model model) {
		//return "/mobile/index.html";
		return "/login.html";
	}
	/**
	 * 获取资料列表
	 */
	@RequestMapping(value = "/queryMeterialList")
	@ResponseBody
	public Object queryMeterialList (Model model,@RequestParam(value = "type",required = false) String type) {
		Wrapper<Meterial> wrapper = new EntityWrapper<>();
		if(ToolUtil.isNotEmpty(type)){
			wrapper.eq("type",type);
		}
        wrapper.eq("is_valid",1);
		List<Meterial> list = meterialMapper.selectList(wrapper);
		return list;
	}

	/**
	 * 获取资料列表
	 */
	@RequestMapping(value = "/queryMeterialListString")
	@ResponseBody
	public String queryMeterialListString (Model model,@RequestParam(value = "type",required = false) String type) {
		String html="";
		Wrapper<Meterial> wrapper = new EntityWrapper<>();
		if(ToolUtil.isNotEmpty(type)){
			wrapper.eq("type",type);
		}
		wrapper.eq("is_valid",1);
		List<Meterial> list = meterialMapper.selectList(wrapper);
		StringBuilder sb = new StringBuilder();
		sb.append("<td>");
		sb.append("<select class='layui-select' id=\"fileName\" name=\"文件夹名\" underline=\"false\">");
		if(ToolUtil.isNotEmpty(list)){
			for(Meterial po: list){
				String meterailName = po.getMeterialName();
				sb.append("<option value=\""+po.getId()+"\" >"+meterailName+"</option>");
			}
		}
		sb.append("</select>");
		sb.append("</td>");
		return sb.toString();
	}

	/**
	 * 获取通知列表
	 */
	@RequestMapping(value = "/getAllType")
	@ResponseBody
	public Object getAllType() {
		Wrapper<MeterialType> type = new EntityWrapper<>();
		type.eq("is_valid",1);
		List<MeterialType> list = meterialTypeMapper.selectList(type);
		StringBuilder sb = new StringBuilder();
		sb.append("<td>");
		sb.append("<select class='layui-select' id=\"type\" name=\"品类名\" underline=\"false\">");
		if(ToolUtil.isNotEmpty(list)){
			for(MeterialType po: list){
				sb.append("<option value=\""+po.getId()+"\" >"+po.getTypeName()+"</option>");
			}
		}
		sb.append("</select>");
		sb.append("</td>");
		return sb.toString();
	}
	/**
	 * 获取所有品类
	 */
	@RequestMapping(value = "/getAllProductType")
	@ResponseBody
	public Object getAllProductType() {
		Wrapper<MeterialType> type = new EntityWrapper<>();
		type.eq("is_valid",1);
		List<MeterialType> list = meterialTypeMapper.selectList(type);
		return list;
	}
	/**
	 * 发送手机短信
	 */
	@GetMapping(value = "/sendSmsCode")
	@ResponseBody
	public Object sendSmsCode (Model model,@RequestParam("mobilePhone")String mobilePhone) {
		SmsCodeDto smsCodeDto = new SmsCodeDto();
		smsCodeDto.setAccount("ZJKJ");
		smsCodeDto.setPassword("147258");
		smsCodeDto.setMobile(mobilePhone);
		smsCodeDto.setAction("send");
		smsCodeDto.setUserid("13372");
		String code;
		Integer code1= (int) ((Math.random() * 100) % 10);
		Integer code2= (int) ((Math.random() * 100) % 10);
		Integer code3= (int) ((Math.random() * 100) % 10);
		Integer code4= (int) ((Math.random() * 100) % 10);
		Integer code5= (int) ((Math.random() * 100) % 10);
		Integer code6= (int) ((Math.random() * 100) % 10);
		code = ""+code1+code2+code3+code4+code5+code6;
		String content="【博登采暖】验证码:"+code+",此条验证码仅适用于登录/注册操作.";
		smsCodeDto.setContent(content);
		String sendUrl="http://lianluxinxi.com/sms.aspx?action="
				+smsCodeDto.getAction()
				+"&userid=" +smsCodeDto.getUserid()
				+"&account="+smsCodeDto.getAccount()
				+"&password="+smsCodeDto.getPassword()
				+"&mobile="+smsCodeDto.getMobile()
				+"&content="+smsCodeDto.getContent()
				+"&sendTime="
				+"&extno=&dataType=json";
		logger.info("发送手机短信的的url{}",sendUrl);
		Map map = new HashMap();
		RestTemplate rest = new RestTemplate();
		String resString = "";
		try{
			rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			resString = rest.postForObject(sendUrl,sendUrl,String.class);
		}catch(Exception e){
			logger.info(e.getMessage());
			logger.info("发送验证码错误"+map.get("message"));
			map.put("message","服务器异常");
			map.put("code",500);
		}
		logger.info(resString);
		JSONObject jsonObject = JSONObject.parseObject(resString);
		logger.info(jsonObject.toJSONString());
		Map resultMap = JSONObject.parseObject(resString,Map.class);
//		//模拟成功测试用
//		Map resultMap = new HashMap();
//		resultMap.put("message","ok");
		if(!"ok".equals(resultMap.get("message"))){
			logger.info("发送验证码错误"+resultMap.get("message"));
			map.put("message","服务器异常");
			map.put("code",500);
		}else{
			map.put("message","ok");
			map.put("code",200);
			map.put("smsCode",code);
		}
		return map;
	}


	/**
	 * 获取某个资料的详情
	 */
	@RequestMapping(value = "/queryMeterialDetails")
	@ResponseBody
	public Object queryMeterialListHtml (Model model,@RequestParam("id")Integer id) {
		Meterial meterial = meterialMapper.selectById(id);
		return meterial;
	}
	/**
	 * 获取某个资料的详情
	 */
	@RequestMapping(value = "/uploadFileByType")
	@ResponseBody
	public Object uploadFileByType (@RequestPart("file") MultipartFile file) {
//		System.out.println("fileTypesdto"+fileTypes.toString());
		Result result = new Result();
		try {
			System.out.println("1111");
			String fileName = "rlb"+ UUID.randomUUID().toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//String newPath = "E:\\临时图片地址\\"+sdf.format(new Date())+"/";

			String newPath = "C:\\boden\\tomcat-8-80\\webapps\\meterial\\"+sdf.format(new Date())+"\\";
			String originName = file.getOriginalFilename();
			File uploadPath = new File(newPath);
			if(!uploadPath.exists()){
				uploadPath.mkdirs();
			}
			System.out.println("222");
			file.transferTo(new File(newPath + fileName+originName));
			String path = "https://www.boden-uk.com/meterial/"+sdf.format(new Date())+"/"+fileName+originName;
//			//文件类型
//			String type ="";
//			//父文件id 没有的话是新建父文件
//			String folderId ="";
//			String remark = "";
//			Integer orderNum=0;
//			System.out.println("333");
//			List<FolderIdDto> folderIdDto = com.alibaba.fastjson.JSON.parseArray(fileTypes.getFileIdParams(), FolderIdDto.class);
//			for (int i = 0; i < folderIdDto.size(); i++) {
//				if(folderIdDto.get(i).getFileName().equals(file.getOriginalFilename())){
//					folderId = folderIdDto.get(i).getFolderId();
//					remark = folderIdDto.get(i).getRemark();
//					orderNum = ToolUtil.isNotEmpty(folderIdDto.get(i).getOrderNum())?Integer.parseInt(folderIdDto.get(i).getOrderNum()):0;
//					System.out.println("folderId:"+folderId+"remark:"+remark+"orderNum:"+orderNum);
//					break;
//				}
//			}
			//选择已有文件夹上传
			MeterialDetail meterialDetail = new MeterialDetail();
			//meterialDetail.setFileId(Integer.parseInt(folderId));
			meterialDetail.setUrl(path);
            //meterialDetail.setRemark(remark);
            //meterialDetail.setOrderNum(orderNum);
			meterialDetail.setIsValid(1);
			meterialDetail.setMeterialName(originName);
			meterialDetailMapper.insert(meterialDetail);
		} catch (Exception e) {
			SysUtils.setErrorMsg(result,"上文件失败!"+e.getMessage());
		}
		return result;
	}
    /**
     * 登录微信公众号
     */
    @RequestMapping(value = "/loginWxCommon")
    @ResponseBody
    public Object loginWxCommon (Model model,@RequestParam("mobilePhone")String mobilePhone) {
        Map map = new HashMap();
    	Wrapper<GzhUser> wrapper = new EntityWrapper<>();
        wrapper.eq("mobile_phone",mobilePhone);
        List list = gzhUserMapper.selectList(wrapper);
		if(list == null||list.size()==0){
            map.put("code",500);
			logger.info(mobilePhone+"未注册,请先注册!");
		}else{
            map.put("code",200);
		}
        return map;
    }

  
	/**
	 * 登记公众号用户信息
	 */
	@RequestMapping(value = "/registerGzhUser")
	@ResponseBody
	public Object registerGzhUser (Model model,@RequestParam("mobilePhone")String mobilePhone
			,@RequestParam("address")String address,@RequestParam("userName")String userName
			,@RequestParam("gender")String gender) {
		Map map = new HashMap();
		map.put("code",200);
		try{
			GzhUser user = new GzhUser();
			user.setAddress(address);
			user.setGender(gender);
			user.setUserName(userName);
			user.setMobilePhone(mobilePhone);
			user.setCreateDate(new Date());
			gzhUserMapper.insert(user);
		}catch (Exception e){
			logger.debug(e.getMessage());
			map.put("code",500);
			return map;
		}
		return map;
	}
}

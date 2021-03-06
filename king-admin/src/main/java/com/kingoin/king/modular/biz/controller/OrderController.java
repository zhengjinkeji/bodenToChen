package com.kingoin.king.modular.biz.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONArray;
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
import com.kingoin.king.core.common.constant.Const;
import com.kingoin.king.core.common.constant.cache.Cache;
import com.kingoin.king.core.common.constant.state.ManagerStatus;
import com.kingoin.king.core.common.exception.BizExceptionEnum;
import com.kingoin.king.core.exception.KingException;
import com.kingoin.king.core.shiro.ShiroKit;
import com.kingoin.king.core.shiro.ShiroUser;
import com.kingoin.king.core.util.ConvertUpMoney;
import com.kingoin.king.core.util.KdniaoTrackQueryAPI;
import com.kingoin.king.core.util.ToolUtil;
import com.kingoin.king.modular.biz.dto.Result;
import com.kingoin.king.modular.biz.model.Banner;
import com.kingoin.king.modular.biz.model.Order;
import com.kingoin.king.modular.biz.service.IOrderService;
import com.kingoin.king.modular.system.model.User;
import com.kingoin.king.modular.system.service.IUserService;

/**
 * <p>
 *	?????????????????????
 * </p>
 * @author serson
 * @date 2019???12???28???
 * @time ??????3:25:35
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	private static String PREFIX = "/biz/order";
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
    private IUserService userService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * ?????????????????????
	 */
	@RequestMapping("/pie_chart")
	public String pieChartIndex() {
		return  "/biz/statistics/pie_chart.html";
	}
	
	/**
	 * ??????????????????
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "/order.html";
	}
	
	/**
	 * ????????????
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String condition,@RequestParam(required = false) String currentStatus) {
		
		List<Map<String, Object>> list = this.orderService.selectOrders(super.getPara("condition"),super.getPara("currentStatus"));
		for(Map<String, Object> m:list) {
			Integer userId = (Integer)m.get("userId");
			if (ToolUtil.isEmpty(userId)) {
	            throw new KingException(BizExceptionEnum.REQUEST_NULL);
	        }
			User user = this.userService.selectById(userId);
			m.put("userAccount", user.getAccount());
			String linMan = (String)m.get("linMan");
			String mobile = (String)m.get("mobile");
			String address = (String)m.get("address");
			m.put("addressStr", address +" "+linMan+" "+mobile);
			
		}
		return list;
	}
	/**
	 * ????????????????????????
	 */
	@RequestMapping(value = "/tracking_add/{id}")
	public String trackingAdd(@PathVariable Integer id, Model model) {
		List<Map<String, Object>> trackings = this.orderService.selectTrackings();
		model.addAttribute("trackings",trackings);
		model.addAttribute("orderId",id);
		return PREFIX + "/tracking_add.html";
	}
	/**
	 * ????????????
	 */
	@RequestMapping(value = "/tracking_add")
	@ResponseBody
	public Tip trackingAdd(@Valid Order order, BindingResult result) {
		if (result.hasErrors()) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Order ord = this.orderService.selectById(order.getId());
		ord.setTrackingCode(order.getTrackingCode());
		ord.setTrackingNum(order.getTrackingNum());
		ord.setStatus("2");
		this.orderService.updateById(ord);
		// ????????????
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
	}
	/**
	 * ????????????
	 */
	@RequestMapping(value = "/logistics/{id}")
	public String logistics(@PathVariable Integer id, Model model) {
		KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
		String result ="";
		try {
			Order ord = this.orderService.selectById(id);
			String trackingCode = ord.getTrackingCode();//????????????????????????
			String trackingNum = ord.getTrackingNum();//????????????
			if(ord!=null && trackingCode!=null && trackingNum!=null) {
				result = api.getOrderTracesByJson(trackingCode, trackingNum);
				JSONArray array = new JSONArray("["+result+"]");
				org.json.JSONObject object = null;
				if(array!=null) {
					Map<String,Object> m = new HashMap<String,Object>();
					object = array.getJSONObject(0);
					String LogisticCode = object.getString("LogisticCode");
					String ShipperCode = object.getString("ShipperCode");
					m.put("LogisticCode", LogisticCode);
					m.put("ShipperCode", ShipperCode);
					model.addAttribute("logistic",m);
					
					String Traces = object.getString("Traces");
					JSONArray TracesArr = new JSONArray(Traces);
					org.json.JSONObject object2 = null;
					List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
					for(int i = 0; i < TracesArr.length(); i++) {
						Map<String,Object> map = new HashMap<String,Object>();
						object2 = TracesArr.getJSONObject(i);
						String AcceptStation = object2.getString("AcceptStation");
						String AcceptTime = object2.getString("AcceptTime");
						map.put("AcceptStation",AcceptStation);
						map.put("AcceptTime",AcceptTime);
						list.add(map);
					}
					Collections.reverse(list);
					model.addAttribute("TracesArr",list);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PREFIX + "/logistics.html";
	}
	/**
	 * ?????????????????????
	 */
	@RequestMapping(value = "/order_edit/{orderId}")
	public String orderEdit(@PathVariable Integer orderId, Model model) {
		if (ToolUtil.isEmpty(orderId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Order ord = this.orderService.selectById(orderId);
		String userName = this.userService.selectById(ord.getUserId()).getAccount();
		model.addAttribute(ord);
		model.addAttribute("userName",userName);
		List<Map<String, Object>> trackings = this.orderService.selectTrackings();
		model.addAttribute("trackings",trackings);
		return PREFIX + "/order_edit.html";
	}
	/**
	 * ??????
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Tip edit(@Valid Order order, BindingResult result) {
		if (result.hasErrors()) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Order ord = this.orderService.selectById(order.getId());
		order.setUserId(ord.getUserId());
		this.orderService.updateById(order);
		
		// ????????????
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
	}
	/**
	 * ????????????
	 */
	@RequestMapping(value = "/detail/{orderId}")
	public String detail(@PathVariable Integer orderId, Model model) {
		if (ToolUtil.isEmpty(orderId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		Order ord = this.orderService.selectById(orderId);//????????????
		User user = this.userService.selectById(ord.getUserId());//????????????
		List<Map<String, Object>> orderDetail = this.orderService.selectOrderDetail(ord.getOrderNum());//????????????
		for(Map<String, Object> m:orderDetail) {
			
			Double price =(Double) m.get("goods_price"); 
			Integer num = (Integer) m.get("goods_num"); 
			Double total = price*num;
			DecimalFormat df = new DecimalFormat("#.00");
			if(total>0) {
				m.put("total",df.format(total));
			}else {
				m.put("total",total);
			}
			
		}
		ConvertUpMoney cu = new ConvertUpMoney();//????????????
		String result = cu.toChinese(ord.getPrice().toString());
		ShiroUser shiroUser =  ShiroKit.getUser();//??????????????????
		String date = sdf2.format(new Date());//???????????? ???????????????
		String dataAdd = sdf.format(ord.getDateAdd());//????????????
		String orderStatus = ord.getStatus();//????????????
		switch (orderStatus) {
		case "0":
			orderStatus = "?????????";
			break;
		case "1":
			orderStatus = "?????????";
			break;
		case "2":
			orderStatus = "?????????";
			break;
		case "3":
			orderStatus = "?????????";
			break;
		case "4":
			orderStatus = "?????????";
			break;
		default:
			break;
		}
		ord.setStatus(orderStatus);
		model.addAttribute("orderDetail",orderDetail);
		model.addAttribute(ord);
		model.addAttribute(user);
		model.addAttribute(shiroUser);
		model.addAttribute("result",result);//??????
		model.addAttribute("date",date);
		model.addAttribute("dataAdd",dataAdd);
		return PREFIX + "/order_print3.html";
	}
	
	/**
	 * ?????????????????????????????????
	 */
	@RequestMapping(value = "/pay_type/{id}")
	public String payType(@PathVariable Integer id, Model model) {
		model.addAttribute("orderId",id);
		return PREFIX + "/pay_type.html";
	}
	/**
	 * ??????????????????
	 */
	@RequestMapping(value = "/pass")
	@ResponseBody
	public Tip pass(@Valid Order order, BindingResult result) {
		if (ToolUtil.isEmpty(order)) {
            throw new KingException(BizExceptionEnum.REQUEST_NULL);
        }
        Order ord = this.orderService.selectById(order.getId());
        ord.setStatus("1");
        ord.setPayType(order.getPayType());
        this.orderService.updateById(ord);
        return SUCCESS_TIP;
	}
	
	/**
	 * ?????????
	 */
	@RequestMapping(value = "/reject")
	@ResponseBody
	public Tip reject(@RequestParam Integer orderId) {
		if (ToolUtil.isEmpty(orderId)) {
            throw new KingException(BizExceptionEnum.REQUEST_NULL);
        }
        Order ord = this.orderService.selectById(orderId);
        ord.setStatus("0");
        ord.setDateClose(new Date(new Date().getTime() + 24* 60 * 60 * 1000));//????????????24????????????????????????
        this.orderService.updateById(ord);
        return SUCCESS_TIP;
	}
	/**
	 * ????????????
	 */
	@RequestMapping(value = "/affirm")
	@ResponseBody
	public Tip affirm(@RequestParam Integer orderId) {
		if (ToolUtil.isEmpty(orderId)) {
            throw new KingException(BizExceptionEnum.REQUEST_NULL);
        }
        Order ord = this.orderService.selectById(orderId);
		/*
		 * List<Map<String, Object>> selectOrderDetail =
		 * this.orderService.selectOrderDetail(ord.getOrderNum()); for(Map<String,
		 * Object> m:selectOrderDetail) { Integer goodsId = (Integer)m.get("goods_id");
		 * 
		 * }
		 */
        ord.setStatus("3");
        ord.setDateFinish(new Date());
        this.orderService.updateById(ord);
        User user = this.userService.selectById(ord.getUserId());
        //??????????????????
		this.orderService.insertXidouRecord(ord.getUserId(), ord.getOrderNum(), "????????????", "+"+ord.getScores(), user.getIntegral()+ord.getScores());
        Integer scores = user.getIntegral()+ord.getScores();
        user.setIntegral(scores);
        this.userService.updateById(user);//????????????
     
        return SUCCESS_TIP;
	}
	/**
	 * ??????
	 */
	@RequestMapping(value = "/remove")
	@ResponseBody
	public Tip remove(@RequestParam Integer orderId) {
		if (ToolUtil.isEmpty(orderId)) {
			throw new KingException(BizExceptionEnum.REQUEST_NULL);
		}
		List<Map<String, Object>> list = this.orderService.selectOrderDetailById(orderId);//????????????
		Map<String, Object> map =list.get(0);
		String status = (String)map.get("status");
		String orderNum = (String) map.get("order_num");
		if(status!=null && "3".equals(status)) {
			
		this.orderService.deleteById(orderId);
		this.orderService.deleteDetailByNum(orderNum);
		}else{
			
			Integer xidou = (Integer)map.get("xidou");//??????????????????
			Integer userId = (Integer)map.get("user_id");//???????????????
			this.orderService.updateUserIntegralById(xidou, userId);//????????????
			List<Map<String, Object>> orderDetail = this.orderService.selectOrderDetail(orderNum);//????????????
        	for(Map<String,Object> m:orderDetail) {
        		Integer goodsId = (Integer)m.get("goods_id");
        		Integer num = (Integer)m.get("goods_num");
        		this.orderService.updateBasicinfoStoreById(num, goodsId);//????????????
        		//??????????????????
        		String  propertyChildIds = (String)m.get("goods_spec");
        		String[] spec = propertyChildIds.split(",");
				for (String s : spec) {
					String[] specIds = s.split(":");
					Integer id = Integer.valueOf(specIds[1]);
					this.orderService.updateSpecStoreById(num, id);
					
				}
        	}
        	this.orderService.deleteById(orderId);
        	this.orderService.deleteDetailByNum(orderNum);
		}
		// ????????????
		CacheKit.removeAll(Cache.CONSTANT);
		return SUCCESS_TIP;
		
	}

}

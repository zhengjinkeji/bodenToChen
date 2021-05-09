package com.kingoin.king.modular.biz.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kingoin.king.modular.biz.model.Order;
import com.kingoin.king.modular.biz.service.IOrderService;
import com.kingoin.king.modular.system.service.IUserService;

/**
 * <p>
 * 定时任务类
 * </p>
 * @author serson
 * @date 2020年1月6日
 * @time 上午11:46:48
 */

@Component
public class SchedulerTask {

	@Autowired
	private IOrderService orderService;
	@Autowired
    private IUserService userService;
	
    @Scheduled(cron="0 * * * * ?")
    private void process(){
    	List<Integer> ids = this.orderService.selectCloseOrder();
    	if(ids!=null && ids.size()>0) {
    		Integer affectedRows = this.orderService.closeOrder(ids);
            System.out.println("this is scheduler task runing  查询到的超时订单 = "+ids+" ,关闭的订单数量 affectedRows = "+affectedRows+" ,关闭时间 "+new Date());
            //返回库存
            for(Integer id:ids) {
            	Order ord = this.orderService.selectById(id);
            	//喜豆流水记录
        		this.orderService.insertXidouRecord(ord.getUserId(), ord.getOrderNum(), "退回喜豆商品", "+"+ord.getXidou(), this.userService.selectById(ord.getUserId()).getIntegral()+ord.getXidou());
            	this.orderService.updateUserIntegralById(ord.getXidou(),ord.getUserId());
            	System.out.println("this is scheduler task runing  返回到个人的喜豆"+ord.getXidou());
            	 
            	List<Map<String,Object>> selectOrderDetail = this.orderService.selectOrderDetail(ord.getOrderNum());
            	for(Map<String,Object> m:selectOrderDetail) {
            		Integer goodsId = (Integer)m.get("goods_id");
            		Integer num = (Integer)m.get("goods_num");
            		this.orderService.updateBasicinfoStoreById(num, goodsId);//归还库存
            		//返还规格库存
            		String  propertyChildIds = (String)m.get("goods_spec");
            		String[] spec = propertyChildIds.split(",");
    				for (String s : spec) {
    					String[] specIds = s.split(":");
    					Integer specid = Integer.valueOf(specIds[1]);
    					this.orderService.updateSpecStoreById(num, specid);
    					
    				}
            	}
            }
    	}
    	
    }

}

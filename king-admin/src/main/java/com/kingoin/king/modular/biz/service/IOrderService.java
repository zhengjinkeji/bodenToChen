package com.kingoin.king.modular.biz.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.kingoin.king.modular.biz.model.Banner;
import com.kingoin.king.modular.biz.model.Order;

/**
 * <p>
 *	订单管理service层
 * </p>
 * 
 * @author serson
 * @date 2019年12月28日
 * @time 下午4:23:40
 */
public interface IOrderService extends IService<Order> {

	List<Map<String, Object>> selectOrders(String condition,String currentStatus);
	
	List<Map<String, Object>> selectTrackings();
	
	List<Map<String, Object>> selectOrderDetail( String orderNum);
	
	List<Integer> selectCloseOrder();
	
	Integer closeOrder(List<Integer> ids);
	
	void updateBasicinfoStoreById(Integer num, Integer id);
	
	void updateUserIntegralById(Integer xidou,Integer id);
	
	List<Map<String, Object>> selectOrderDetailById(Integer id);
	
	void insertXidouRecord( Integer userId, String orderNum,
			 String typeStr,String xidou, Integer balance);
	
	void deleteDetailByNum(String orderNum);
	
	void updateSpecStoreById(Integer num,Integer id);
}

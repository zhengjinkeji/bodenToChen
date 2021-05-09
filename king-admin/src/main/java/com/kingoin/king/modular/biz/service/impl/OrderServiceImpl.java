package com.kingoin.king.modular.biz.service.impl;
/**
 * <p>
 *
 * </p>
 * @author serson
 * @date 2019年12月28日
 * @time 下午4:23:40
 */

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kingoin.king.modular.biz.dao.OrderMapper;
import com.kingoin.king.modular.biz.model.Order;
import com.kingoin.king.modular.biz.service.IOrderService;
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService{

	@Resource
	private OrderMapper orderMapper;
	
	@Override
	public List<Map<String, Object>> selectOrders(String condition,String currentStatus) {
		
		return this.orderMapper.selectOrders(condition,currentStatus);
	}

	@Override
	public List<Map<String, Object>> selectTrackings() {
		
		return this.orderMapper.selectTrackings();
	}

	@Override
	public List<Map<String, Object>> selectOrderDetail(String orderNum) {
		
		return this.orderMapper.selectOrderDetail(orderNum);
	}

	@Override
	public Integer closeOrder(List<Integer> ids) {
		
		return this.orderMapper.closeOrder(ids);
	}

	@Override
	public List<Integer> selectCloseOrder() {
		
		return this.orderMapper.selectCloseOrder();
	}

	@Override
	public void updateBasicinfoStoreById(Integer num, Integer id) {
		
		this.orderMapper.updateBasicinfoStoreById(num,id);
	}

	@Override
	public void updateUserIntegralById(Integer xidou, Integer id) {
		
		this.orderMapper.updateUserIntegralById(xidou,id);
	}

	@Override
	public List<Map<String, Object>> selectOrderDetailById(Integer id) {
		
		return this.orderMapper.selectOrderDetailById(id);
	}

	@Override
	public void insertXidouRecord(Integer userId, String orderNum, String typeStr, String xidou, Integer balance) {
		
		this.orderMapper.insertXidouRecord(userId, orderNum, typeStr, xidou, balance);
	}

	@Override
	public void deleteDetailByNum(String orderNum) {
		
		this.orderMapper.deleteDetailByNum(orderNum);
	}

	@Override
	public void updateSpecStoreById(Integer num, Integer id) {
		
		this.orderMapper.updateSpecStoreById(num,id);
	}

}

package com.kingoin.king.modular.biz.dao;
/**
 * <p>
 *
 * </p>
 * @author serson
 * @date 2019年11月16日
 * @time 下午9:37:54
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kingoin.king.modular.biz.model.Order;

public interface OrderMapper extends BaseMapper<Order> {
	/**
	 * 根据条件查询列表
	 *
	 */
	List<Map<String, Object>> selectOrders(@Param("condition") String condition,@Param("currentStatus") String currentStatus);
	/**
	 * 查询快递公司
	 */
	List<Map<String, Object>> selectTrackings();
	// 查询订单详情
	List<Map<String, Object>> selectOrderDetail(@Param("orderNum") String orderNum);
	
	/**
	 * 查询需要关闭的订单
	 */
	List<Integer> selectCloseOrder();
	/**
	 * 关闭超时订单
	 */
	Integer closeOrder(List<Integer> ids);
	
	//返回商品库存
	void updateBasicinfoStoreById(@Param("num") Integer num,@Param("id") Integer id);
	
	//返回用户喜豆
	void updateUserIntegralById(@Param("xidou") Integer xidou,@Param("id") Integer id);
	// 根据id查询订单
	List<Map<String, Object>> selectOrderDetailById(@Param("id") Integer id);
	//新增喜豆流水
	void insertXidouRecord(@Param("userId") Integer userId,@Param("orderNum") String orderNum,
			@Param("typeStr") String typeStr,@Param("xidou") String xidou,@Param("balance") Integer balance);
	
	//删除订单详情
	void deleteDetailByNum(@Param("orderNum") String orderNum);
	
	//返还规格相应库存
	void updateSpecStoreById(@Param("num") Integer num,@Param("id") Integer id);
}

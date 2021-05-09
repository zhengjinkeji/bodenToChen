package com.kingoin.king.modular.biz.dao;
/**
 * <p>
 *	mapper接口
 * </p>
 * @author serson
 * @date 2019年12月27日
 * @time 下午
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CheckMapper {

	// 查询新注册用户
	List<Map<String, Object>> selectRegisterUser(@Param("title")String title);
	
	//查询新注册用户详情
	List<Map<String, Object>> selectRegisterUserInfo(@Param("account")String account);

}

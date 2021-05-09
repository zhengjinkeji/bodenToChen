package com.kingoin.king.modular.biz.service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *	待审核用户服务层
 * </p>
 * @author serson
 * @date 2019年12月27日
 * @time 下午6:26:24
 */
public interface ICheckService {

	// 查询新注册用户
	List<Map<String, Object>> selectRegisterUser(String title);
	
	List<Map<String, Object>> selectRegisterUserInfo(String account);
}

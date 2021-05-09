package com.kingoin.king.modular.biz.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingoin.king.modular.biz.dao.CheckMapper;
import com.kingoin.king.modular.biz.service.ICheckService;

/**
 * <p>
 *
 * </p>
 * @author serson
 * @date 2019年12月27日
 * @time 下午6:27:04
 */
@Service
public class CheckServiceImpl implements ICheckService{

	@Resource
	private CheckMapper checkMapper;
	
	@Override
	public List<Map<String, Object>> selectRegisterUser(String title) {
		
		return this.checkMapper.selectRegisterUser(title);
	}

	@Override
	public List<Map<String, Object>> selectRegisterUserInfo(String account) {
		
		return this.checkMapper.selectRegisterUserInfo(account);
	}

}

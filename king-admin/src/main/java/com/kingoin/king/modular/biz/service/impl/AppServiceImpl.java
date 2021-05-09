package com.kingoin.king.modular.biz.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingoin.king.modular.biz.dao.AppMapper;
import com.kingoin.king.modular.biz.dto.UserInfo;
import com.kingoin.king.modular.biz.service.IAppService;

/**
 * <p>
 * APP 服务实现类
 * </p>
 * 
 * @author serson
 * @date 2019年11月28日
 * @time 上午11:58:30
 */
@Service
@Transactional
public class AppServiceImpl implements IAppService {

	@Resource
	private AppMapper appMapper;

	@Override
	public List<Map<String, Object>> selectStartPics() {

		return this.appMapper.selectStartPics();
	}

	@Override
	public List<Map<String, Object>> selectCateBanners() {

		return this.appMapper.selectCateBanners();
	}

	@Override
	public List<Map<String, Object>> selectIndexBanners() {

		return this.appMapper.selectIndexBanners();
	}

	@Override
	public void addUserInfo(UserInfo user) {

		this.appMapper.addUserInfo(user);
	}

	@Override
	public List<Map<String, Object>> selectRceommend() {
		
		return this.appMapper.selectRceommend();
	}

	@Override
	public String selectCoverPic(Integer basicinfoId) {
		
		return this.appMapper.selectCoverPic(basicinfoId);
	}

}

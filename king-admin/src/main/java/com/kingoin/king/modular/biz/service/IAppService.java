package com.kingoin.king.modular.biz.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kingoin.king.modular.biz.dto.UserInfo;

/**
 * <p>
 * 服务层接口
 * </p>
 * 
 * @author serson
 * @date 2019年11月28日
 * @time 上午11:58:01
 */
public interface IAppService {

	List<Map<String, Object>> selectStartPics();// 1

	List<Map<String, Object>> selectIndexBanners();// 2

	List<Map<String, Object>> selectCateBanners();// 3

	void addUserInfo(UserInfo user);// 4
	
	List<Map<String, Object>> selectRceommend();

	String selectCoverPic(Integer basicinfoId);

}

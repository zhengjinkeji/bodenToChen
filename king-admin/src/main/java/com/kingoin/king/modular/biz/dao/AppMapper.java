package com.kingoin.king.modular.biz.dao;
/**
 * <p>
 *	mapper接口
 * </p>
 * @author serson
 * @date 2019年11月16日
 * @time 下午9:37:54
 */

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.kingoin.king.modular.biz.dto.UserInfo;

public interface AppMapper {

	// 查询启动图1
	List<Map<String, Object>> selectStartPics();

	// 查询首页轮播图3
	List<Map<String, Object>> selectIndexBanners();

	// 查询分类轮播图2
	List<Map<String, Object>> selectCateBanners();

	// 添加UserInfo 4
	void addUserInfo(@Param("user") UserInfo user);
	
	//查询推荐商品
	List<Map<String, Object>> selectRceommend();
	
	// 查询商品是否有封面
	String selectCoverPic(@Param("basicinfoId") Integer basicinfoId);

}

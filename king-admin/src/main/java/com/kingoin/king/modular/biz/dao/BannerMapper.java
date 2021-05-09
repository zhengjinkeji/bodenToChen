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
import com.kingoin.king.modular.biz.model.Banner;

public interface BannerMapper extends BaseMapper<Banner> {
	/**
	 * 根据条件查询列表
	 *
	 */
	List<Map<String, Object>> selectBanners(@Param("condition") String condition);
	
}

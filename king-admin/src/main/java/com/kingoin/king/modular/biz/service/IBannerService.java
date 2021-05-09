package com.kingoin.king.modular.biz.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.kingoin.king.modular.biz.model.Banner;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年11月16日
 * @time 下午9:57:18
 */
public interface IBannerService extends IService<Banner> {

	List<Map<String, Object>> selectBanners(@Param("condition") String condition);

}

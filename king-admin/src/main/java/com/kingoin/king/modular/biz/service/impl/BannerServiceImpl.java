package com.kingoin.king.modular.biz.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kingoin.king.modular.biz.dao.BannerMapper;
import com.kingoin.king.modular.biz.model.Banner;
import com.kingoin.king.modular.biz.service.IBannerService;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年11月16日
 * @time 下午9:58:48
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {

	@Resource
	private BannerMapper bannerMapper;

	@Override
	public List<Map<String, Object>> selectBanners(String condition) {

		return this.bannerMapper.selectBanners(condition);
	}

}

package com.kingoin.king.modular.biz.warpper;

import java.util.Map;

import com.kingoin.king.core.base.warpper.BaseControllerWarpper;
import com.kingoin.king.core.common.constant.factory.ConstantFactory;

/**
 * <p>
 *	规格管理包装类
 * </p>
 * @author serson
 * @date 2019年11月25日
 * @time 下午1:16:48
 */
public class BannerWarpper extends BaseControllerWarpper{

	public BannerWarpper(Object obj) {
		super(obj);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		
		 map.put("statusStr", ConstantFactory.me().getDictStatusName((Integer) map.get("status")));
		 map.put("typeStr", ConstantFactory.me().getDictTypeName((Integer) map.get("type")));
	}

}

package com.kingoin.king.modular.biz.warpper;

import java.util.Map;

import com.kingoin.king.core.base.warpper.BaseControllerWarpper;
import com.kingoin.king.core.common.constant.factory.ConstantFactory;

/**
 * <p>
 *	商品包装类
 * </p>
 * @author serson
 * @date 2019年11月25日
 * @time 下午4:50:04
 */
public class BasicinfoWapper extends BaseControllerWarpper{

	public BasicinfoWapper(Object obj) {
		super(obj);
		
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		map.put("statusStr", ConstantFactory.me().getDictStatusName((Integer) map.get("status")));
		map.put("recommendStr", ConstantFactory.me().getDictYesNoName((Integer) map.get("recommend")));
	}

}

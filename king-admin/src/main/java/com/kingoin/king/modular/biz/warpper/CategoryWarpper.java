package com.kingoin.king.modular.biz.warpper;

import java.util.Map;

import com.kingoin.king.core.base.warpper.BaseControllerWarpper;
import com.kingoin.king.core.common.constant.factory.ConstantFactory;

/**
 * <p>
 * 商品类别管理
 * </p>
 * @author serson
 * @date 2019年11月25日
 * @time 下午4:15:42
 */
public class CategoryWarpper extends BaseControllerWarpper{

	public CategoryWarpper(Object obj) {
		super(obj);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {

		map.put("statusStr", ConstantFactory.me().getDictStatusName((Integer) map.get("status")));
		map.put("levelStr", ConstantFactory.me().getDictLevelName((Integer) map.get("level")));
		
	}

}

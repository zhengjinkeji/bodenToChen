package com.kingoin.king.modular.biz.warpper;

import java.util.List;
import java.util.Map;

import com.kingoin.king.core.base.warpper.BaseControllerWarpper;
import com.kingoin.king.core.common.constant.factory.ConstantFactory;
import com.kingoin.king.core.util.ToolUtil;
import com.kingoin.king.modular.biz.model.Specification;
import com.kingoin.king.modular.system.model.Dict;

/**
 * <p>
 *	规格管理包装类
 * </p>
 * @author serson
 * @date 2019年11月25日
 * @time 下午1:16:48
 */
public class SpecWarpper extends BaseControllerWarpper{

	public SpecWarpper(Object obj) {
		super(obj);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		StringBuffer detail = new StringBuffer();
        Integer id = (Integer) map.get("id");
        List<Specification> dicts = ConstantFactory.me().findInSpec(id);
        if(dicts != null){
            for (Specification dict : dicts) {
                detail.append(dict.getNum() + ":" +dict.getName() + ",");
            }
            map.put("detail", ToolUtil.removeSuffix(detail.toString(),","));
        }
		
	}

}

package com.kingoin.king.modular.system.warpper;

import com.kingoin.king.core.common.constant.factory.ConstantFactory;
import com.kingoin.king.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 包装
 *
 * @author jack
 * @date 2017年4月25日 18:10:31
 */
public class NoticeWrapper extends BaseControllerWarpper {

    public NoticeWrapper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
    	String type = (String) map.get("type");
    	switch (type) {
		case "1":
			map.put("type","图片资料");
			break;
		case "2":
			map.put("type","视频资料");
			break;
		case "3":
			map.put("type","文档资料");
			break;
		default:
			break;
		}
    }

}

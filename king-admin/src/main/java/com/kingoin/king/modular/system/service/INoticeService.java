package com.kingoin.king.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.kingoin.king.modular.system.model.Notice;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 服务类
 * </p>
 *
 * @author jack123
 * @since 2018-02-22
 */
public interface INoticeService extends IService<Notice> {
	
	/**
     * 获取通知列表 old 用于显示首页 关联sys_notice
     */
    List<Map<String, Object>> listOld(String condition);
    /**
     * 获取通知列表
     */
    List<Map<String, Object>> list(String condition);
    /**
     * 根据类型 获取列表
     */
    List<Map<String, Object>> listByType(Integer condition);
    
}

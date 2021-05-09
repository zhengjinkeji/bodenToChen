package com.kingoin.king.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kingoin.king.modular.system.model.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 Mapper 接口
 * </p>
 *
 * @author jack
 * @since 2017-07-11
 */
public interface NoticeMapper extends BaseMapper<Notice> {
	
	/**
     * 获取通知列表  old 用于显示首页 关联sys_notice
     */
    List<Map<String, Object>> listOld(@Param("condition") String condition);
    /**
     * 获取通知列表
     */
    List<Map<String, Object>> list(@Param("condition") String condition);
    /**
     * 根据类型 获取列表
     */
    List<Map<String, Object>> listByType(@Param("condition") Integer condition);

}
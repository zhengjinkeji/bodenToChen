package com.kingoin.king.modular.biz.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kingoin.king.modular.biz.model.Specification;

/**
 * <p>
 * 规格管理接口
 * </p>
 * 
 * @author serson
 * @date 2019年11月25日
 * @time 上午11:31:47
 */
public interface SpecMapper extends BaseMapper<Specification> {
	/**
	 * 根据列表
	 */
	// List<Dict> selectByCode(@Param("pid") String pid);
	// List<Dict> selectByCode(@Param("code") String code);victor
	/**
	 * 查询列表
	 */
	List<Map<String, Object>> list(@Param("condition") String conditiion);

	/**
	 * 查询所有子类
	 */
	List<Map<String, Object>> selectChildByPid(@Param("pid") Integer pid);

	/**
	 * 查询规格相关字典值
	 */
	List<Map<String, Object>> selectSpecType(@Param("typeName") String typeName);
}

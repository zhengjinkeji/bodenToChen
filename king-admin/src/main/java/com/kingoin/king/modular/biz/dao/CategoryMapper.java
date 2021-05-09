package com.kingoin.king.modular.biz.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kingoin.king.modular.biz.model.Category;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年11月19日
 * @time 下午1:40:33
 */
public interface CategoryMapper extends BaseMapper<Category> {

	/**
	 * 根据条件查询列表
	 *
	 */
	List<Map<String, Object>> selectCategory(@Param("name") String name);

	/**
	 * 查询PID PNAME
	 */
	List<Map<String, Object>> selectCategoryLevels();

	/**
	 * 根据PID 查询下级
	 */
	List<Category> selectCategoryByPid(@Param("pid") Integer pid);
}

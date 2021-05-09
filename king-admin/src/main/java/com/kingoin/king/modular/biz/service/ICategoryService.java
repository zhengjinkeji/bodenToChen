package com.kingoin.king.modular.biz.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.kingoin.king.modular.biz.model.Category;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年11月19日
 * @time 下午1:41:54
 */

public interface ICategoryService extends IService<Category> {

	List<Map<String, Object>> selectCategory(@Param("name") String name);

	List<Map<String, Object>> selectCategoryLevels();

	List<Category> selectCategoryByPid(@Param("pid") Integer pid);
}

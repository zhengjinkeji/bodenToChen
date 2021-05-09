package com.kingoin.king.modular.biz.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kingoin.king.modular.biz.dao.CategoryMapper;
import com.kingoin.king.modular.biz.model.Category;
import com.kingoin.king.modular.biz.service.ICategoryService;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年11月19日
 * @time 下午1:43:06
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

	@Resource
	private CategoryMapper categoryMapper;

	@Override
	public List<Map<String, Object>> selectCategory(String name) {

		return this.categoryMapper.selectCategory(name);
	}

	@Override
	public List<Map<String, Object>> selectCategoryLevels() {

		return this.categoryMapper.selectCategoryLevels();
	}

	@Override
	public List<Category> selectCategoryByPid(Integer pid) {

		return this.categoryMapper.selectCategoryByPid(pid);
	}

}

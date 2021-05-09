package com.kingoin.king.modular.biz.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.kingoin.king.modular.biz.model.Specification;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年11月25日
 * @time 上午11:35:16
 */
public interface ISpecService extends IService<Specification> {
	/**
	 * 添加规格
	 */
	void addSpec(String SpecName, String typeName, String SpecValues);

	/**
	 * 编辑规格
	 */
	void editSpec(Integer SpecId, String typeName, String SpecName, String Specs);

	/**
	 * 删除规格
	 */
	void delteSpec(Integer SpecId);

	/**
	 * 根据编码获取词典列表
	 */
	// List<Spec> selectByCode(@Param("code") String code);

	/**
	 * 查询规格列表
	 */
	List<Map<String, Object>> list(@Param("condition") String conditiion);

	List<Map<String, Object>> selectChildByPid(@Param("pid") Integer pid);

	List<Map<String, Object>> selectSpecType(String typeName);
}

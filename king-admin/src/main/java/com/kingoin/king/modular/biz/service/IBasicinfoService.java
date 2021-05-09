package com.kingoin.king.modular.biz.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.kingoin.king.core.node.ZTreeNode;
import com.kingoin.king.modular.biz.dto.NumSpec;
import com.kingoin.king.modular.biz.model.Basicinfo;
import com.kingoin.king.modular.biz.model.Picture;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年11月21日
 * @time 下午1:30:43
 */
public interface IBasicinfoService extends IService<Basicinfo> {

	List<Map<String, Object>> selectBasicinfos(Page<Basicinfo> page, @Param("condition") String condition, String orderByField, boolean isAsc);

	List<Map<String, Object>> selectBasicinfoDzs(@Param("condition") String condition);
	
	List<Map<String, Object>> selectBasicinfoXds(@Param("condition") String condition);
	
	List<Map<String, Object>> selectCategory();

	List<Map<String, Object>> selectCategoryDz();
	
	List<Map<String, Object>> selectCategoryXd();
	
	List<Map<String, Object>> selectPicByBasicinfoId(@Param("basicinfoId") Integer basicinfoId);

	void insertPic(@Param("picture") Picture picture);

	void updatePic(@Param("picture") Picture picture);

	void deletePicById(@Param("picId") Integer picId);

	String selectCoverPic(@Param("basicinfoId") Integer basicinfoId);

	void updateCoverPic(@Param("basicinfoId") Integer basicinfoId, String updateUser);

	List<Long> getSpecIdsByBasicinfoId(@Param("basicinfoId") Integer basicinfoId);

	List<ZTreeNode> specTreeList();

	List<ZTreeNode> specTreeListBySpecIds(List<Long> menuIds);

	void setSpec(Integer basicinfoId, String ids);

	// void deleteBasicinfo(Basicinfo basicinfo);逻辑删除代码
	void deleteBasicinfoById(Integer basicinfoId);
	
	List<Map<String, Object>> selectBascinfoNumSpec(@Param("basicinfoId") Integer basicinfoId);
	
	void addNumspec(@Param("ns") NumSpec ns);
	
	NumSpec selectNumSpecByid(@Param("id") Integer id);
	
	void updateNumspec(@Param("ns") NumSpec ns);
	
	void deleteNumspecById(@Param("id") Integer id);
}

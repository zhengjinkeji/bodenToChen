package com.kingoin.king.modular.biz.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
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
 * @time 下午1:28:23
 */
public interface BasicinfoMapper extends BaseMapper<Basicinfo> {

	/**
	 * 根据条件查询品牌商品列表
	 *
	 */
	List<Map<String, Object>> selectBasicinfos(@Param("page") Page<Basicinfo> page, @Param("condition") String condition,@Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);

	/**
	 * 根据条件查询品牌定制商品列表
	 *
	 */
	List<Map<String, Object>> selectBasicinfoDzs(@Param("condition") String condition);
	/**
	 * 根据条件查询喜豆商品列表
	 *
	 */
	List<Map<String, Object>> selectBasicinfoXds(@Param("condition") String condition);

	/**
	 * 查询类别 品牌
	 */
	List<Map<String, Object>> selectCategory();
	
	/**
	 * 查询类别 品牌定制
	 */
	List<Map<String, Object>> selectCategoryDz();
	/**
	 * 查询类别 喜豆
	 */
	List<Map<String, Object>> selectCategoryXd();

	/**
	 * 根据商品ID查图片
	 */
	List<Map<String, Object>> selectPicByBasicinfoId(@Param("basicinfoId") Integer basicinfoId);

	/**
	 * 新增图片
	 */
	void insertPic(@Param("picture") Picture picture);

	/**
	 * 修改图片
	 */
	void updatePic(@Param("picture") Picture picture);

	/**
	 * 删除图片
	 */
	void deletePicById(@Param("picId") Integer picId);

	/**
	 * 删除某商品的图片
	 */
	void deleteBasicinfoPicByBasicinfoId(@Param("basicinfoId") Integer basicinfoId);

	/**
	 * 查询商品是否有封面
	 */
	String selectCoverPic(@Param("basicinfoId") Integer basicinfoId);

	/**
	 * 清除该商品所有封面
	 */
	void updateCoverPic(@Param("basicinfoId") Integer basicinfoId, @Param("updateUser") String updateUser);

	/**
	 * 根据条件查询规格
	 *
	 * @return
	 * @date 2017年2月12日 下午9:14:34
	 */
	List<Long> getSpecIdsByBasicinfoId(@Param("basicinfoId") Integer basicinfoId);

	/**
	 * 获取规格列表树
	 *
	 */
	List<ZTreeNode> specTreeList();

	/**
	 * 
	 */
	List<ZTreeNode> specTreeListBySpecIds(List<Long> menuIds);

	/**
	 * 删除某个商品的所有规格
	 */
	int deleteSpecById(@Param("basicinfoId") Integer basicinfoId);
	/**
	 * 逻辑删除某个商品的所有图片
	 * 
	 * int updatePicByBaiscinfoId(@Param("basicinfoId") Integer
	 * basicinfoId,@Param("updateUser") String updateUser);
	 */
	/**
	 * 查询商品的数量配置
	 */
	List<Map<String, Object>> selectBascinfoNumSpec(@Param("basicinfoId") Integer basicinfoId);
	/**
	 * 增加数量配置
	 */
	void addNumspec(@Param("ns") NumSpec ns);
	
	NumSpec selectNumSpecByid(@Param("id") Integer id);
	
	void updateNumspec(@Param("ns") NumSpec ns);
	
	void deleteNumspecById(@Param("id") Integer id);
}

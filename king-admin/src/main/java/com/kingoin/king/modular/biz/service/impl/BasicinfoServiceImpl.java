package com.kingoin.king.modular.biz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kingoin.king.core.node.ZTreeNode;
import com.kingoin.king.core.shiro.ShiroKit;
import com.kingoin.king.core.util.Convert;
import com.kingoin.king.modular.biz.dao.BasicinfoMapper;
import com.kingoin.king.modular.biz.dao.Relation2Mapper;
import com.kingoin.king.modular.biz.dto.NumSpec;
import com.kingoin.king.modular.biz.model.Basicinfo;
import com.kingoin.king.modular.biz.model.Picture;
import com.kingoin.king.modular.biz.model.Relation2;
import com.kingoin.king.modular.biz.service.IBasicinfoService;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年11月21日
 * @time 下午1:31:22
 */
@Service
public class BasicinfoServiceImpl extends ServiceImpl<BasicinfoMapper, Basicinfo> implements IBasicinfoService {

	@Resource
	private BasicinfoMapper basicinfoMapper;

	@Resource
	private Relation2Mapper relation2Mapper;

	@Override
	public List<Map<String, Object>> selectBasicinfos(Page<Basicinfo> page, String condition, String orderByField, boolean isAsc) {

		return this.basicinfoMapper.selectBasicinfos(page,condition,orderByField,isAsc);
	}
	
	@Override
	public List<Map<String, Object>> selectBasicinfoDzs(String condition) {
		
		return this.basicinfoMapper.selectBasicinfoDzs(condition);
	}
	
	@Override
	public List<Map<String, Object>> selectBasicinfoXds(String condition) {
		
		return this.basicinfoMapper.selectBasicinfoXds(condition);
	}

	@Override
	public List<Map<String, Object>> selectCategory() {

		return this.basicinfoMapper.selectCategory();
	}
	
	@Override
	public List<Map<String, Object>> selectCategoryDz() {
		
		return this.basicinfoMapper.selectCategoryDz();
	}

	@Override
	public List<Map<String, Object>> selectCategoryXd() {
		
		return this.basicinfoMapper.selectCategoryXd();
	}
	
	@Override
	public List<Map<String, Object>> selectPicByBasicinfoId(Integer basicinfoId) {

		return this.basicinfoMapper.selectPicByBasicinfoId(basicinfoId);
	}

	@Override
	public void insertPic(Picture picture) {

		this.basicinfoMapper.insertPic(picture);
	}

	@Override
	public void updatePic(Picture picture) {

		this.basicinfoMapper.updatePic(picture);
	}

	@Override
	public void deletePicById(Integer picId) {

		this.basicinfoMapper.deletePicById(picId);
	}

	@Override
	public String selectCoverPic(Integer basicinfoId) {

		return this.basicinfoMapper.selectCoverPic(basicinfoId);
	}

	@Override
	public void updateCoverPic(Integer basicinfoId, String updateUser) {

		this.basicinfoMapper.updateCoverPic(basicinfoId, updateUser);
	}

	@Override
	public List<Long> getSpecIdsByBasicinfoId(Integer basicinfoId) {

		return this.basicinfoMapper.getSpecIdsByBasicinfoId(basicinfoId);
	}

	@Override
	public List<ZTreeNode> specTreeList() {

		return this.basicinfoMapper.specTreeList();
	}

	@Override
	public List<ZTreeNode> specTreeListBySpecIds(List<Long> menuIds) {

		return this.basicinfoMapper.specTreeListBySpecIds(menuIds);
	}

	@Override
	@Transactional(readOnly = false)
	public void setSpec(Integer basicinfoId, String ids) {
		// 删除该商品所有的规格
		this.basicinfoMapper.deleteSpecById(basicinfoId);
		// 添加新的规格
		for (Long id : Convert.toLongArray(true, Convert.toStrArray(",", ids))) {
			Relation2 relation = new Relation2();
			relation.setAddUser(ShiroKit.getUser().getName());
			relation.setDateAdd(new Date());
			relation.setSpecId(id);
			relation.setBasicinfoId(basicinfoId);
			this.relation2Mapper.insert(relation);
		}
	}

	@Override
	public void deleteBasicinfoById(Integer basicinfoId) {
		// public void deleteBasicinfo(Basicinfo basicinfo) {

		// 删除该商品关联的规格
		this.basicinfoMapper.deleteSpecById(basicinfoId);
		// 逻辑删除该商品关联的图片
		// this.basicinfoMapper.updatePicByBaiscinfoId(basicinfo.getId(),ShiroKit.getUser().getName());
		// 删除该商品关联的图片 非逻辑
		this.basicinfoMapper.deleteBasicinfoPicByBasicinfoId(basicinfoId);
		// 逻辑删除商品
		// this.basicinfoMapper.updateById(basicinfo);
		// 删除商品
		this.basicinfoMapper.deleteById(basicinfoId);
	}

	@Override
	public List<Map<String, Object>> selectBascinfoNumSpec(Integer basicinfoId) {
		
		return this.basicinfoMapper.selectBascinfoNumSpec(basicinfoId);
	}

	@Override
	public void addNumspec(NumSpec ns) {
		
		this.basicinfoMapper.addNumspec(ns);
	}

	@Override
	public NumSpec selectNumSpecByid(Integer id) {
		
		return this.basicinfoMapper.selectNumSpecByid(id);
	}

	@Override
	public void updateNumspec(NumSpec ns) {
		
		this.basicinfoMapper.updateNumspec(ns);
	}

	@Override
	public void deleteNumspecById(Integer id) {
		
		this.basicinfoMapper.deleteNumspecById(id);
	}

	
	

	

	
	

	

}

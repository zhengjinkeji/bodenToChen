package com.kingoin.king.modular.biz.service.impl;

import static com.kingoin.king.core.common.constant.factory.MutiStrFactory.MUTI_STR_KEY;
import static com.kingoin.king.core.common.constant.factory.MutiStrFactory.MUTI_STR_VALUE;
import static com.kingoin.king.core.common.constant.factory.MutiStrFactory.MUTI_STR_VALUE2;
import static com.kingoin.king.core.common.constant.factory.MutiStrFactory.parseKeyValue;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kingoin.king.core.common.exception.BizExceptionEnum;
import com.kingoin.king.core.exception.KingException;
import com.kingoin.king.core.shiro.ShiroKit;
import com.kingoin.king.modular.biz.dao.SpecMapper;
import com.kingoin.king.modular.biz.model.Specification;
import com.kingoin.king.modular.biz.service.ISpecService;
import com.kingoin.king.modular.system.model.Dict;

/**
 * <p>
 * 规格管理服务实现类
 * </p>
 * 
 * @author serson
 * @date 2019年11月25日
 * @time 上午11:37:18
 */
@Service
@Transactional
public class SpecServiceImpl extends ServiceImpl<SpecMapper, Specification> implements ISpecService {

	@Resource
	private SpecMapper specMapper;

	@Override
	public void addSpec(String SpecName, String typeName, String SpecValues) {
		// 判断有没有值
		List<Specification> dicts = specMapper
				.selectList(new EntityWrapper<Specification>().eq("name", SpecName).and().eq("pid", 0));
		if (dicts != null && dicts.size() > 0) {
			throw new KingException(BizExceptionEnum.DICT_EXISTED2);
		}
		// 解析dictValues
		List<Map<String, String>> items = parseKeyValue(SpecValues);
		// 添加
		Specification spec = new Specification();
		spec.setName(SpecName);
		spec.setNum(0.0);
		spec.setPid(0);
		// spec.setStatus(1);
		spec.setTypeName(typeName);
		spec.setAddUser(ShiroKit.getUser().getName());
		spec.setDateAdd(new Date());
		this.specMapper.insert(spec);
		// 添加字典条目
		for (Map<String, String> item : items) {
			String num = item.get(MUTI_STR_KEY);
			String name = item.get(MUTI_STR_VALUE);
			String stroe = item.get(MUTI_STR_VALUE2);
			Specification itemDict = new Specification();
			itemDict.setPid(spec.getId());
			itemDict.setName(name);
			itemDict.setStore(Integer.parseInt(stroe));
			try {
				itemDict.setNum(Double.valueOf(num));
			} catch (NumberFormatException e) {
				throw new KingException(BizExceptionEnum.DICT_MUST_BE_NUMBER);
			}
			this.specMapper.insert(itemDict);
		}
	}

	@Override
	public void editSpec(Integer SpecId, String typeName, String SpecName, String Specs) {
		/// 删除之前的字典
		this.delteSpec(SpecId);

		// 重新添加新的字典
		this.addSpec(SpecName, typeName, Specs);

	}

	@Override
	public void delteSpec(Integer SpecId) {
		// 删除这个字典的子词典
		Wrapper<Specification> dictEntityWrapper = new EntityWrapper<>();
		dictEntityWrapper = dictEntityWrapper.eq("pid", SpecId);
		specMapper.delete(dictEntityWrapper);
		// 删除这个词典
		specMapper.deleteById(SpecId);

	}

	@Override
	public List<Map<String, Object>> list(String conditiion) {

		return this.baseMapper.list(conditiion);
	}

	@Override
	public List<Map<String, Object>> selectChildByPid(Integer pid) {

		return this.baseMapper.selectChildByPid(pid);
	}

	@Override
	public List<Map<String, Object>> selectSpecType(String typeName) {

		return this.specMapper.selectSpecType(typeName);
	}

}

package com.kingoin.king.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kingoin.king.modular.system.dao.RelationMapper;
import com.kingoin.king.modular.system.model.Relation;
import com.kingoin.king.modular.system.service.IRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author jack123
 * @since 2018-02-22
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {

}

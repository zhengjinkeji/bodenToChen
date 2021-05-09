package com.kingoin.king.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kingoin.king.modular.system.model.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author jack
 * @since 2017-07-11
 */
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 根据编码获取词典列表
     */
    List<Dict> selectByCode(@Param("pid") String pid);
  //List<Dict> selectByCode(@Param("code") String code);victor
    /**
     * 查询字典列表
     */
    List<Map<String, Object>> list(@Param("condition") String conditiion);
}
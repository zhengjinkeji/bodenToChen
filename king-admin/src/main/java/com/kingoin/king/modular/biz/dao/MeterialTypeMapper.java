package com.kingoin.king.modular.biz.dao;
/**
 * <p>
 *
 * </p>
 * @author serson
 * @date 2019年11月16日
 * @time 下午9:37:54
 */

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kingoin.king.modular.biz.model.Meterial;
import com.kingoin.king.modular.biz.model.MeterialType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MeterialTypeMapper extends BaseMapper<MeterialType> {

    List<Map<String, Object>> selectMeterialTypeList(@Param("condition") String condition);
}

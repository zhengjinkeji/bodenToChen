package com.kingoin.king.core.common.constant.factory;

import java.util.Date;
import java.util.List;

import com.kingoin.king.modular.biz.model.Specification;
import com.kingoin.king.modular.system.model.Dict;

/**
 * 常量生产工厂的接口
 *
 * @author jack
 * @date 2017-06-14 21:12
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     * @author jack
     * @Date 2017/5/9 23:41
     */
    String getUserNameById(Integer userId);
    /**
     * 把日期格式转成字符串 时分
     */
    String getSdfHM(Date d);
    /**
     * 把日期格式转成字符串 年月日
     */
    String getSdfYMD(Date d);
    /**
     * 根据用户id获取用户账号
     *
     * @author jack
     * @date 2017年5月16日21:55:371
     */
    String getUserAccountById(Integer userId);

    /**
     * 通过角色ids获取角色名称
     */
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Integer roleId);
    /**
     * 通过物业id获取物业名称
    
    String getSinglePropName(Integer propId);
     */
    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Integer roleId);

    /**
     * 获取部门名称
     */
    String getDeptName(Integer deptId);

    /**
     * 获取菜单的名称们(多个)
     */
    String getMenuNames(String menuIds);

    /**
     * 获取菜单名称
     */
    String getMenuName(Long menuId);

    /**
     * 获取菜单名称通过编号
     */
    String getMenuNameByCode(String code);

    /**
     * 获取字典名称
     */
    String getDictName(Integer dictId);

    /**
     * 获取通知标题
     */
    String getNoticeTitle(Integer dictId);

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, Integer val);
    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);
    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);

    /**
     * 获取菜单状态
     */
    String getMenuStatusName(Integer status);

    /**
     * 查询字典
     */
    List<Dict> findInDict(Integer id);

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);

    /**
     * 获取子部门id
     */
    List<Integer> getSubDeptId(Integer deptid);

    /**
     * 获取所有父部门id
     */
    List<Integer> getParentDeptIds(Integer deptid);
    /**
     * 查询规格
     */
    List<Specification> findInSpec(Integer id);
    /**
     * 获取规格名称
     */
    String getSpecName(Integer specId);
    /**
     * 获取状态名称
     */
    String getDictStatusName(Integer statusVal);
    /**
     * 获取类别名称
     */
    String getDictTypeName(Integer typeVal);
    /**
     * 获取层级名称
     */
    String getDictLevelName(Integer levelVal);
    /**
     * 获取图片类别名称
     */
    String getDictPicTypeName(Integer picTypeVal);
    /**
    * 获取是
    */
   String getDictYesNoName(Integer yesNo);

    /**
     * 获取营业执照路径
     */
    String getDictBusinessLicenseUrl(Integer num);
}

package com.kingoin.king.core.beetl;

import com.kingoin.king.core.util.KaptchaUtil;
import com.kingoin.king.core.util.ToolUtil;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

/**
 * beetl拓展配置,绑定一些工具类,方便在模板中直接调用
 *
 * @author jack
 * @Date 2018/2/22 21:03
 */
public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    @Override
    public void initOther() {
        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
        groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());
    }
}

package com.kingoin.king.core.util;

import com.kingoin.king.config.properties.KingProperties;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 获取验证码开关
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(KingProperties.class).getKaptchaOpen();
    }
}
package com.kingoin.king;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * King Web程序启动类
 *
 * @author jack
 * @date 2017-05-21 9:43
 */
public class KingServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(KingApplication.class);
    }
}

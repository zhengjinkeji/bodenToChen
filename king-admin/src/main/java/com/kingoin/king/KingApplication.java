package com.kingoin.king;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * SpringBoot方式启动类
 *
 * @author jack
 * @Date 2017/5/21 12:06
 * 
 * 修改
 */
@EnableScheduling
@SpringBootApplication
public class KingApplication {

    private final static Logger logger = LoggerFactory.getLogger(KingApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KingApplication.class, args);
        
        logger.info("KingApplication is success!");
    }
    
}

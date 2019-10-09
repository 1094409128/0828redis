package com.aaa.zxz.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ProjectName: 0828redis
 * @Package: com.aaa.zxz.redis
 * @Author: zxz
 * @CreateDate: 2019/8/28 19:18
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.aaa.zxz.redis.mapper")
public class ApplicationRun {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }
}

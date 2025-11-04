package com.newhker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 新港人生活号应用启动类
 */
@SpringBootApplication
@MapperScan("com.newhker.mapper")
@EnableCaching
public class NewHkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewHkerApplication.class, args);
        System.out.println("========================================");
        System.out.println("新港人生活号后端系统启动成功！");
        System.out.println("========================================");
    }
}

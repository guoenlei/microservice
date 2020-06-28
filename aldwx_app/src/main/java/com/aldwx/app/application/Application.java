package com.aldwx.app.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@Configuration
@ComponentScan("com.aldwx.app")
//@MapperScan("com.aldwx.app.common.database.mysql.Dynamic")
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class Application {
    public static void main(String[] args) {
        //设置时区
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(Application.class, args);
    }
}

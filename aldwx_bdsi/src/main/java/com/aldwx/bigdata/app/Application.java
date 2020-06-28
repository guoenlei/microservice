package com.aldwx.bigdata.app;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.aldwx")
//@ServletComponentScan
@SpringBootApplication/*(exclude = PageHelperAutoConfiguration.class)*/
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

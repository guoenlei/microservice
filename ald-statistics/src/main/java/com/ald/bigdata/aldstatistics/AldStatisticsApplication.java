package com.ald.bigdata.aldstatistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("com.ald")
public class AldStatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AldStatisticsApplication.class, args);
    }

}

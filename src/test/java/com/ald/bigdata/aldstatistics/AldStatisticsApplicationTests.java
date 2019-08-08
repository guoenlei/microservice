package com.ald.bigdata.aldstatistics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AldStatisticsApplicationTests {


    @Test
    public void getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); // 注册数据库驱动
            String url = "jdbc:mysql://10.0.221.7:3306/ald_game"; // briup为数据库名称
            conn = DriverManager.getConnection(url, "tongjitest", "Aa4iep5ne~uv"); // 获取连接数据库的Connection对象
            if (conn == null) {
                System.out.println(conn+":空");
            } else {
                System.out.println(conn+":非空");
            }
            System.out.println("数据库连接成功！" + conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AldStatisticsApplicationTests().getConnection();
    }


}

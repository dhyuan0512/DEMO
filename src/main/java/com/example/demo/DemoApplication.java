package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@Slf4j
public class DemoApplication {

    /**
     * 端口
     */
    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    /**
     * 启动成功
     */
    @Bean
    public ApplicationRunner applicationRunner() {
        return applicationArguments -> {
            try {
                //获取本机内网IP
                log.info("启动成功：" + "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port);
                //获取本机Swagger
                log.info("启动成功Swagger：" + "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/swagger-ui.html");
            } catch (UnknownHostException e) {
                //输出到日志文件中
                log.error(e.getMessage());
            }
        };
    }

}

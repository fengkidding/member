package com.member;

import com.member.config.FinalEnvConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

/**
 * 启动类
 *
 * @author f
 * @date 2019-03-10
 */
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableEurekaClient
@EnableAsync
public class MemberApplication {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.profiles.active}")
    private String env;

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        FinalEnvConfig.setAppName(appName);
        FinalEnvConfig.setEnv(env);
    }
}



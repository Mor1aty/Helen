package com.moriaty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2019/8/29 15:55
 * @Description TODO
 *   配置中心，本地配置方式
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigureApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigureApplication.class, args);
    }
}

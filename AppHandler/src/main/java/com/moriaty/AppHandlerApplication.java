package com.moriaty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2019/8/29 15:54
 * @Description TODO
 *   App 请求处理服务
 */
@SpringBootApplication
@EnableFeignClients
public class AppHandlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppHandlerApplication.class, args);
    }
}

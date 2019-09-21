package com.moriaty.feign;

import com.moriaty.entity.Admin;
import com.moriaty.entity.Secret;
import com.moriaty.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/8/29 15:53
 * @Description TODO
 * User 服务请求 Feign
 */
@FeignClient("user")
public interface UserFeign {

    @GetMapping("/user/login/user/{username}/{password}")
    public User loginCommon(@PathVariable("username") String username, @PathVariable("password") String password);

    @GetMapping("/user/login/admin/{username}/{password}")
    public Admin loginAdmin(@PathVariable("username") String username, @PathVariable("password") String password);

    @GetMapping("/user/getCurrentSecret")
    public Secret getCurrentSecret();
}

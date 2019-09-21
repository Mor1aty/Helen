package com.moriaty.controller;

import com.moriaty.entity.Admin;
import com.moriaty.entity.Secret;
import com.moriaty.entity.User;
import com.moriaty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/8/29 15:56
 * @Description TODO
 * User 服务 Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    private UserRepository userRepository;

    @GetMapping("/login/user/{username}/{password}")
    public User loginCommon(@PathVariable("username") String username, @PathVariable("password") String password) {
        return userRepository.getUser(username, password);
    }

    @GetMapping("/login/admin/{username}/{password}")
    public Admin loginAdmin(@PathVariable("username") String username, @PathVariable("password") String password) {
        return userRepository.getAdmin(username, password);
    }

    @GetMapping("/getCurrentSecret")
    public Secret getCurrentSecret() {
        return userRepository.getCurrentSecret();
    }

}

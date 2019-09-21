package com.moriaty.controller;

import com.alibaba.fastjson.JSON;
import com.moriaty.entity.Admin;
import com.moriaty.entity.Secret;
import com.moriaty.entity.User;
import com.moriaty.feign.UserFeign;
import com.moriaty.utils.GlobalConstant;
import com.moriaty.utils.JWTUtil;
import com.moriaty.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/8/29 15:51
 * @Description TODO
 * User 服务请求处理
 */
@RestController
@RequestMapping("/appUser")
public class UserController {
    @Autowired
    private UserFeign userFeign;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session, HttpServletResponse response) {
        Secret secret = userFeign.getCurrentSecret();
        try {
            switch (type) {
                case "user":
                    User user = userFeign.loginCommon(username, password);
                    if (user == null) {
                        return JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "用户名密码错误");
                    }
                    user.setCreateTime(null);
                    response = JWTUtil.plaintextEncrypt(response, JWTUtil.plaintextBase64Encrypt(JSON.toJSONString(user)), GlobalConstant.PUBLIC_KEY);
                    return JsonUtil.jsonResponse(GlobalConstant.CODE_SUCCESS, user, "登录成功");
                case "admin":
                    Admin admin = userFeign.loginAdmin(username, password);
                    if (admin == null) {
                        return JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "用户名密码错误");
                    }
                    // 将密钥对存入 session
                    session.setAttribute("secret", secret);
                    response = JWTUtil.plaintextEncrypt(response, JSON.toJSONString(admin), secret.getPublicKey());
                    return JsonUtil.jsonResponse(GlobalConstant.CODE_SUCCESS, admin, "登录成功");
            }
            return JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "登陆类型错误");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "服务器发生错误");
        }
    }

    @GetMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        response = JWTUtil.removeCookies(request, response);
        return JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "注销成功");
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}

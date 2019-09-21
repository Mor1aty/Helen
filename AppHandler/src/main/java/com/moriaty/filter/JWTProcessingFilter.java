package com.moriaty.filter;


import com.moriaty.entity.Secret;
import com.moriaty.utils.GlobalConstant;
import com.moriaty.utils.JWTUtil;
import com.moriaty.utils.JsonUtil;
import com.moriaty.utils.SecretKeyUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/6/17 19:41
 * @Description TODO
 * JWT 过滤器，用于处理登录
 */
public class JWTProcessingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 转化ServletRequest为HttpRequest
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        try {
            if (req.getServletPath().equals("/appUser/login") && req.getMethod().equals("POST")) {
                // 登录接口
                chain.doFilter(req, resp);
            } else {
                // 非登录接口
                // 使用私钥解密，比对
                String plaintext = JWTUtil.getPlaintext(req, "plaintext");
                String ciphertext = JWTUtil.getPlaintext(req, "ciphertext");
                if (plaintext != null && ciphertext != null) {
                    String decrypt = SecretKeyUtil.decrypt(ciphertext, GlobalConstant.PRIVATE_KEY);
                    if (decrypt.equals(plaintext)) {
                        chain.doFilter(req, resp);
                    } else {
                        resp.getWriter().write(JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "请先登录"));
                    }
                } else {
                    resp.getWriter().write(JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "请先登录"));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "服务器发生错误"));
        }


    }

    @Override
    public void destroy() {

    }
}

package com.moriaty.utils;

import com.alibaba.fastjson.JSON;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/6/18 10:01
 * @Description TODO
 * JWT 抽象工具
 */
public class JWTUtil {

    /**
     * 获取明文 String
     *
     * @param request
     * @param plaintextName
     * @return
     */
    public static String getPlaintext(HttpServletRequest request, String plaintextName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (plaintextName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 获取明文 String
     *
     * @param request
     * @return
     */
    public static String getPlaintextJson(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("plaintext".equals(cookie.getName())) {
                    byte[] bytes = Base64.decodeBase64(cookie.getValue());
                    return new String(bytes);
                }
            }
        }
        return null;
    }

    /**
     * 获取明文 Map
     *
     * @param request
     * @return
     */
    public static HashMap<String, Object> getPlaintextMap(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("plaintext".equals(cookie.getName())) {
                    byte[] bytes = Base64.decodeBase64(cookie.getValue());
                    return JSON.parseObject(new String(bytes), HashMap.class);
                }
            }
        }
        return null;
    }

    /**
     * 获取密文
     *
     * @param request
     * @param ciphertextName
     * @return
     */
    public static String getCiphertext(HttpServletRequest request, String ciphertextName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ciphertextName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 明文加密，将明文与加密后明文存储到 Cookie 中
     *
     * @param response
     * @param plaintext
     * @return
     * @throws Exception
     */
    public static HttpServletResponse plaintextEncrypt(HttpServletResponse response, String plaintext, String publicKey) throws Exception {
        // 将帐号公钥加密存储，以及明文存储到 Cookie 中
        String encrypt = SecretKeyUtil.encrypt(plaintext, publicKey);
        Cookie plaintextCookie = new Cookie("plaintext", plaintext);
        plaintextCookie.setPath("/");
        Cookie ciphertextCookie = new Cookie("ciphertext", encrypt);
        ciphertextCookie.setPath("/");
        response.addCookie(plaintextCookie);
        response.addCookie(ciphertextCookie);
        return response;
    }

    /**
     * 删除明文密文 Cookie
     *
     * @param request
     * @param response
     * @return
     */
    public static HttpServletResponse removeCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        int flag = 0;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("plaintext") || cookie.getName().equals("ciphertext")) {
                flag++;
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                if (flag == 2) {
                    break;
                }
            }
        }
        return response;
    }

    /**
     * 明文 JSON Base64 加密
     *
     * @param plaintextJson
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String plaintextBase64Encrypt(String plaintextJson) throws UnsupportedEncodingException {
        byte[] plaintextByte = Base64.encodeBase64(plaintextJson.getBytes("UTF-8"));
        return new String(plaintextByte);
    }
}

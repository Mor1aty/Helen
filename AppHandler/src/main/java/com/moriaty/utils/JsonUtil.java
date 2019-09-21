package com.moriaty.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/8/29 15:19
 * @Description TODO
 * Json 生成工具
 */
public class JsonUtil {

    public static String jsonResponse(Integer code, Object data, String msg) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("data", data);
        map.put("msg", msg);
        return JSON.toJSONString(map);
    }
}

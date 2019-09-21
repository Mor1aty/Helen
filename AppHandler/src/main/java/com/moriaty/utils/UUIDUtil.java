package com.moriaty.utils;

import java.util.UUID;

/**
 * @author 16计算机弓耀
 * @version 1.0
 * @copyright ：神农大学生软件创新中心 版权所有 © 2017
 * @date 2017年10月17日 下午4:12:19
 * @Description TODO
 * UUID工具类
 */

public class UUIDUtil {
    //得到 32 位 UUID，对得到的UUID进行去“-”处理
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}


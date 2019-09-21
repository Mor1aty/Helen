package com.moriaty.entity;

import lombok.Data;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/8/30 14:28
 * @Description TODO
 * 文件夹表 Entity
 */
@Data
public class Folder {
    private long id;
    private String name;
    private User creator;
    private String createTime;
}

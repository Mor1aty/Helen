package com.moriaty.entity;

import lombok.Data;

/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2019/8/30 15:20
 * @Description TODO
 *   文件表 Entity
 */
@Data
public class File {
    private long id;
    private String location;
    private String name;
    private Filetype type;
    private User up;
    private String upTime;
    private Folder folder;
}

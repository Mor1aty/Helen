package com.moriaty.entity;

import lombok.Data;

/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2019/8/30 15:21
 * @Description TODO
 *  文件类型表 Entity
 */
@Data
public class Filetype {
    private String suffix;
    private String desc;
    private int isDownload;
    private int isPreview;
}

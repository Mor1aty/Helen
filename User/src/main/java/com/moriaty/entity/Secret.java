package com.moriaty.entity;

import lombok.Data;

/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2019/8/29 16:13
 * @Description TODO
 *   密钥表 Entity
 */
@Data
public class Secret {
    private long id;
    private String publicKey;
    private String privateKey;
    private String createTime;
    private int isUse;
    private String discardTime;
    private String discardReason;
}

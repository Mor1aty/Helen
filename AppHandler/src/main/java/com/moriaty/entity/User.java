package com.moriaty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2019/8/29 15:53
 * @Description TODO
 *   用户表 Entity
 */
@Data
public class User {
    private String username;
    private String nickname;
    private int gender;
    private String createTime;
}

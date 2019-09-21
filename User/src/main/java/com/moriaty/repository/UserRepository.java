package com.moriaty.repository;

import com.moriaty.entity.Admin;
import com.moriaty.entity.Secret;
import com.moriaty.entity.User;
import org.apache.ibatis.annotations.Select;
/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2019/8/29 15:57
 * @Description TODO
 *   User 持久库
 */
public interface UserRepository {

    /**
     * 用户名密码获取用户
     * @param username
     * @param password
     * @return User
     */
    @Select("SELECT * FROM t_user WHERE username = #{username} AND password = #{password}")
    public User getUser(String username, String password);

    /**
     * 用户名密码获取管理员
     * @param username
     * @param password
     * @return Admin
     */
    @Select("SELECT * FROM t_admin WHERE username = #{username} AND password = #{password}")
    public Admin getAdmin(String username, String password);

    /**
     * 获取当前可用密钥对
     * @return Secret
     */
    @Select("SELECT * FROM t_secret WHERE is_use = 1")
    public Secret getCurrentSecret();

}

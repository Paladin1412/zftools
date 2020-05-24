package com.ziroom.zftools.service;

import com.ziroom.zftools.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 根据username查询用户信息
     *
     * @param username
     * @return
     */
    User queryUserByUsername(String username);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<User> queryAllUser();

    /**
     * 插入一条用户信息
     *
     * @param user
     * @return
     */
    boolean insertUser(User user);

    /**
     * 检查一条登录用户的密码是否正确
     *
     * @param user
     * @return
     */
    boolean checkUserLoginInfo(User user);

}

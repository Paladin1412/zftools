package com.ziroom.zftools.dao;

import com.ziroom.zftools.entity.User;

import java.util.List;

public interface UserDao {

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
    int insertUser(User user);

}

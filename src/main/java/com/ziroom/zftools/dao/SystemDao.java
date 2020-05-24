package com.ziroom.zftools.dao;

import com.ziroom.zftools.entity.System;

import java.util.List;

public interface SystemDao {

    /**
     * 根据id查询系统信息
     *
     * @param id
     * @return
     */
    System querySystemById(int id);

    /**
     * 查询所有系统信息
     *
     * @return
     */
    List<System> queryAllSystem();

}

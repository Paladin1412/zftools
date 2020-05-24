package com.ziroom.zftools.service;

import com.ziroom.zftools.entity.System;

import java.util.List;

public interface SystemService {

    /**
     * 根据sysId查询系统信息
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

package com.ziroom.zftools.service.impl;

import com.ziroom.zftools.dao.SystemDao;
import com.ziroom.zftools.entity.System;
import com.ziroom.zftools.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    private SystemDao systemDao;

    @Override
    public System querySystemById(int id) {
        return systemDao.querySystemById(id);
    }

    @Override
    public List<System> queryAllSystem() {
        return systemDao.queryAllSystem();
    }

}

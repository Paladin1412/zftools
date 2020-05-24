package com.ziroom.zftools.service.impl;

import com.ziroom.zftools.dao.DatabaseDao;
import com.ziroom.zftools.entity.Database;
import com.ziroom.zftools.service.DatabaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Resource
    private DatabaseDao databaseDao;

    @Override
    public Database queryDatabaseById(int id) {
        return databaseDao.queryDatabaseById(id);
    }

    @Override
    public List<Database> queryAllDatabase() {
        return databaseDao.queryAllDatabase();
    }

}

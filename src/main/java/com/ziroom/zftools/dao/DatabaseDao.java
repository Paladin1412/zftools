package com.ziroom.zftools.dao;

import com.ziroom.zftools.entity.Database;

import java.util.List;

public interface DatabaseDao {

    /**
     * 根据id查询数据库信息
     *
     * @param id
     * @return
     */
    Database queryDatabaseById(int id);

    /**
     * 查询所有数据库信息
     *
     * @return
     */
    List<Database> queryAllDatabase();

}

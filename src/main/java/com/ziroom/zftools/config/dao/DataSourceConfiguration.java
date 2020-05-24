package com.ziroom.zftools.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * 配置dataSource到IOC容器中
 */
@Configuration
// 配置Mybatis Mapper的扫描路径
@MapperScan("com.ziroom.zftools.dao")
public class DataSourceConfiguration {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * 生成与spring-dao.xml对应的bean - dataSource
     *
     * @return
     * @throws PropertyVetoException
     */
    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        // 生成datasource实例
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        // 跟配置文件一样设置以下信息
        // 驱动
        comboPooledDataSource.setDriverClass(driver);
        // 数据库连接URL
        comboPooledDataSource.setJdbcUrl(url);
        // 设置用户名
        comboPooledDataSource.setUser(username);
        // 设置用户密码
        comboPooledDataSource.setPassword(password);
        // 关闭连接后不自动commit
        comboPooledDataSource.setAutoCommitOnClose(false);
        // 连接失败重试次数
        comboPooledDataSource.setAcquireRetryAttempts(2);
        return comboPooledDataSource;
    }

}

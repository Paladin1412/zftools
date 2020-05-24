package com.ziroom.zftools.service.impl;

import com.ziroom.zftools.dao.UserDao;
import com.ziroom.zftools.entity.User;
import com.ziroom.zftools.enums.ExceptionCode;
import com.ziroom.zftools.exception.BaseException;
import com.ziroom.zftools.service.UserService;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Override
    public User queryUserByUsername(String username) {
        return userDao.queryUserByUsername(username);
    }

    @Override
    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    @Transactional
    public boolean insertUser(User user) {
        if (user.getUsername() != null && !user.getUsername().equals("") && user.getPassword() != null && !user.getPassword().equals("")) {
            user.setUid(UUID.randomUUID().toString());
            int effectedNum = 0;
            try {
                effectedNum = userDao.insertUser(user);
            } catch (MyBatisSystemException e) {
                logger.info("数据库连接失败");
                throw new BaseException(ExceptionCode.DB_CONNECT_ERROR, "数据库连接失败");
            } catch (Exception e) {
                logger.info("用户已注册" + user);
                throw new BaseException(ExceptionCode.USER_HAS_LOGON_ERROR, "用户已注册");
            }
            if (effectedNum > 0) {
                return true;
            } else {
                logger.info("插入用户信息失败" + user);
                throw new BaseException(ExceptionCode.DB_INSERT_ERROR, "插入用户信息失败");
            }
        } else {
            logger.info("用户信息不能为空" + ", username = " + user.getUsername() + ", password = " + user.getPassword());
            throw new BaseException(ExceptionCode.INPUT_ERROR, "用户信息不能为空");
        }
    }

    @Override
    public boolean checkUserLoginInfo(User user) {
        if (user.getUsername() != null && !user.getUsername().equals("") && user.getPassword() != null && !user.getPassword().equals("")) {
            String dbPassword = "";
            try {
                dbPassword = queryUserByUsername(user.getUsername()).getPassword();
            } catch (MyBatisSystemException e) {
                logger.info("数据库连接失败");
                throw new BaseException(ExceptionCode.DB_CONNECT_ERROR, "数据库连接失败");
            } catch (Exception e) {
                logger.info("用户未注册" + "username = " + user.getUsername());
                throw new BaseException(ExceptionCode.USER_NOT_LOGON_ERROR, "用户未注册");
            }
            if (dbPassword.equals(user.getPassword())) {
                return true;
            } else {
                logger.info("用户密码不匹配：" + "username = " + user.getUsername() + ", password = " + user.getPassword() + ", db password = " + dbPassword);
                throw new BaseException(ExceptionCode.USER_PASSWORD_MISMATCH, "用户密码不匹配");
            }
        } else {
            logger.info("用户信息不能为空" + ", username = " + user.getUsername() + ", password = " + user.getPassword());
            throw new BaseException(ExceptionCode.INPUT_ERROR, "用户信息不能为空");
        }
    }

}

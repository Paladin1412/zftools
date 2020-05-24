package com.ziroom.zftools.controller;

import com.alibaba.fastjson.JSON;
import com.ziroom.zftools.common.CommonResponse;
import com.ziroom.zftools.entity.User;
import com.ziroom.zftools.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    CommonResponse response = new CommonResponse();

    @Resource
    private UserService userService;

    @GetMapping("/list_one")
    public CommonResponse listOne(String username) {
        logger.info("===listOne start===");
        logger.info("username = " + username);
        long startTime = System.currentTimeMillis();
        response.setCode(200);
        response.setMessage("success");
        response.setData(JSON.toJSONString(userService.queryUserByUsername(username)));
        logger.info("response = " + response);
        long endTime = System.currentTimeMillis();
        logger.info("costTime:[{}ms]", endTime - startTime);
        logger.info("===listOne end===");
        return response;
    }

    @GetMapping("/list_all")
    public CommonResponse listAll() {
        logger.info("===listAll start===");
        long startTime = System.currentTimeMillis();
        response.setCode(200);
        response.setMessage("success");
        response.setData(JSON.toJSONString(userService.queryAllUser()));
        logger.info("response = " + response);
        long endTime = System.currentTimeMillis();
        logger.info("costTime:[{}ms]", endTime - startTime);
        logger.info("===listAll end===");
        return response;
    }

    @PostMapping("/add")
    public CommonResponse add(User user) {
        logger.info("===add start===");
        logger.info("user = " + user);
        long startTime = System.currentTimeMillis();
        response.setCode(200);
        response.setMessage("success");
        if(userService.insertUser(user)){
            response.setData("注册成功");
        }else{
            response.setData("注册失败");
        }
        logger.info("response = " + response);
        long endTime = System.currentTimeMillis();
        logger.info("costTime:[{}ms]", endTime - startTime);
        logger.info("===add end===");
        return response;
    }

    @PostMapping("/check_login_info")
    @ResponseBody
    public CommonResponse checkUserLoginInfo(User user) {
        logger.info("===checkUserLoginInfo start===");
        long startTime = System.currentTimeMillis();
        response.setCode(200);
        response.setMessage("success");
        if(userService.checkUserLoginInfo(user)){
            response.setData("用户名与密码匹配");
        }else{
            response.setData("用户名与密码不匹配");
        }
        logger.info("response = " + response);
        long endTime = System.currentTimeMillis();
        logger.info("costTime:[{}ms]", endTime - startTime);
        logger.info("===checkUserLoginInfo end===");
        //向session中加入user参数，提供页面展示
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute("user", JSON.toJSONString(user));
        return response;
    }

}

package com.ziroom.zftools.controller;

import com.ziroom.zftools.common.CommonResponse;
import com.ziroom.zftools.entity.User;
import com.ziroom.zftools.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LoginAndRegisterController {

    Logger logger = LoggerFactory.getLogger(LoginAndRegisterController.class);
    CommonResponse response = new CommonResponse();

    @Resource
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login_and_register";
    }

    @PostMapping("/register")
    @ResponseBody
    public CommonResponse registerNewUser(User user) {
        logger.info("===register start===");
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
        logger.info("===register end===");
        return response;
    }

}

package com.ziroom.zftools.controller;

import com.alibaba.fastjson.JSON;
import com.ziroom.zftools.common.CommonResponse;
import com.ziroom.zftools.entity.User;
import com.ziroom.zftools.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);
    CommonResponse response = new CommonResponse();

    @Resource
    private SystemService systemService;

    @GetMapping("/home_page")
    public String login() {
        return "home";
    }

    @GetMapping("/home_get_user_info")
    @ResponseBody
    public CommonResponse getUserInfo() {
        logger.info("===getUserInfo start===");
        long startTime = System.currentTimeMillis();
        //从session中拿user参数，提供页面展示
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = JSON.parseObject((String) request.getSession().getAttribute("user"), User.class);
        response.setCode(200);
        response.setMessage("success");
        response.setData(JSON.toJSONString(user));
        logger.info("response = " + response);
        long endTime = System.currentTimeMillis();
        logger.info("costTime:[{}ms]", endTime - startTime);
        logger.info("===getUserInfo end===");
        return response;
    }

    @GetMapping("/home_get_system_navigation")
    @ResponseBody
    public CommonResponse getSystemNavigationList() {
        logger.info("===getSystemNavigationList start===");
        long startTime = System.currentTimeMillis();
        response.setCode(200);
        response.setMessage("success");
        response.setData(JSON.toJSONString(systemService.queryAllSystem()));
        logger.info("response = " + response);
        long endTime = System.currentTimeMillis();
        logger.info("costTime:[{}ms]", endTime - startTime);
        logger.info("===getSystemNavigationList end===");
        return response;
    }

}

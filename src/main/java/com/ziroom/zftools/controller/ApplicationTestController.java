package com.ziroom.zftools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationTestController {

    @GetMapping("/test_string")
    @ResponseBody
    public String testString(){
        return "test string...";
    }

    @GetMapping("/test_page")
    public String testPage(){
        return "login_and_register";
    }

}

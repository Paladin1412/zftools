package com.ziroom.zftools.handler;

import com.ziroom.zftools.common.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse exceptionHandler(HttpServletRequest request, Exception e) {
        logger.info("Exception Message = " + e.getMessage());
        CommonResponse response = new CommonResponse();
        response.setCode(0);
        response.setMessage("failed");
        response.setData(e.getMessage());
        return response;
    }

}

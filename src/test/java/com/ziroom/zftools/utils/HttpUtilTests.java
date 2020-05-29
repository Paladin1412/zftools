package com.ziroom.zftools.utils;

import com.ziroom.zftools.utils.http.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HttpUtilTests {

    Logger logger = LoggerFactory.getLogger(HttpUtilTests.class);

    @Resource
    public HttpClient httpClient;

    @Test
    void testGet() throws Exception {
        HttpClient hc = httpClient;
        HttpResponse hr = HttpUtil.doGet("http://localhost:8080", "/home_get_system_navigation");
        logger.info(HttpUtil.getJson(hr).toString());
    }

}

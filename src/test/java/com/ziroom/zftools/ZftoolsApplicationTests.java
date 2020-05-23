package com.ziroom.zftools;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZftoolsApplicationTests {

    Logger logger = LoggerFactory.getLogger(ZftoolsApplicationTests.class);

    @Test
    void contextLoads() {
        logger.info("SpringBoot单元测试的模板类");
    }

}

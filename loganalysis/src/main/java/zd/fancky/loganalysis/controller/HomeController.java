package zd.fancky.loganalysis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther fancky
 * @Date 2020-10-7 9:17
 * @Description
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/logTest")
    public void logTest() {
        //Spring Boot默认的日志级别为INFO
        //日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
    }
}

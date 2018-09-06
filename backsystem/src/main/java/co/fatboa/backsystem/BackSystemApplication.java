package co.fatboa.backsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @Author: hl
 * @Description: TODO
 * @Date: 12:21 2018/8/25
 * @Modified By:
 * @Version 1.0
 */
@SpringBootApplication
public class BackSystemApplication {
    static Logger logger = LoggerFactory.getLogger(BackSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackSystemApplication.class, args);
        logger.info("【【【【【消息队列-消息提供者启动成功.】】】】】");
    }
}

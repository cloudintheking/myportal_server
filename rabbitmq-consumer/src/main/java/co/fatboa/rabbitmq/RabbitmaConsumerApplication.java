package co.fatboa.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: hl
 * @Date: 2018/9/6 13:37
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@SpringBootApplication
public class RabbitmaConsumerApplication {
    static Logger logger = LoggerFactory.getLogger(RabbitmaConsumerApplication.class);
    /**
     * rabbitmq消费者启动入口
     * @param args
     */
    public static void main(String[] args)
    {
        SpringApplication.run(RabbitmaConsumerApplication.class,args);

        logger.info("【【【【【消息队列-消息消费者启动成功.】】】】】");
    }
}

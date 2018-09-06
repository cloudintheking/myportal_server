package co.fatboa.rabbitmq.consumer;

import co.fatboa.rabbitmq.common.enums.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: hl
 * @Date: 2018/9/6 14:31
 * @Description: 用户注册消息消费者
 * @Modified By:
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "user.register.queue")
public class UserConsumer {
    private static Logger logger = LoggerFactory.getLogger(UserConsumer.class);

    @RabbitHandler
    public void excute(String userId) {
        logger.info("用户：" + userId + "完成了注册");
        // 其他业务处理...
    }
}

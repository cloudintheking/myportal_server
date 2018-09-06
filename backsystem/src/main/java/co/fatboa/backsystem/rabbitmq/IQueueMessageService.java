package co.fatboa.backsystem.rabbitmq;

import co.fatboa.rabbitmq.common.enums.ExchangeEnum;
import co.fatboa.rabbitmq.common.enums.QueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Auther: hl
 * @Date: 2018/9/6 13:16
 * @Description: 消息队列业务接口
 * @Modified By:
 * @Version 1.0
 */
public interface IQueueMessageService extends RabbitTemplate.ConfirmCallback {
    /**
     * 发送消息到rabbitmq消息队列
     *
     * @param message      消息内容
     * @param exchangeEnum 交换配置枚举
     * @param queueEnum    队列配置枚举
     * @throws Exception
     */
    void send(Object message, ExchangeEnum exchangeEnum, QueueEnum queueEnum) throws Exception;
}

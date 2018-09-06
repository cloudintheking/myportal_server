package co.fatboa.backsystem.rabbitmq.Impl;

import co.fatboa.backsystem.rabbitmq.IQueueMessageService;
import co.fatboa.rabbitmq.common.enums.ExchangeEnum;
import co.fatboa.rabbitmq.common.enums.QueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Auther: hl
 * @Date: 2018/9/6 13:19
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Component
public class QueueMessageServiceImpl implements IQueueMessageService {
    @Autowired
    private RabbitTemplate rabbitTemplate; //消息队列模板

    /**
     * 发送消息到rabbitmq消息队列
     *
     * @param message      消息内容
     * @param exchangeEnum 交换配置枚举
     * @param queueEnum    队列配置枚举
     * @throws Exception
     */
    @Override
    public void send(Object message, ExchangeEnum exchangeEnum, QueueEnum queueEnum) throws Exception {
        rabbitTemplate.setConfirmCallback(this);  //设置回调为当前类对象
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString()); //构建回调id为uuid
        rabbitTemplate.convertAndSend(exchangeEnum.getValue(), queueEnum.getRoutingKey(), message, correlationId); //发送消息到消息队列
    }

    /**
     * 消息回调确认方法
     *
     * @param correlationData 请求数据对象
     * @param ack             是否发送成功
     * @param cause           失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(" 回调id:" + correlationData.getId());
        if (ack) {
            System.out.println("消息发送成功");
        } else {
            System.out.println("消息发送失败:" + cause);
        }
    }
}

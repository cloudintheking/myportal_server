package co.fatboa.rabbitmq.common.enums;

import lombok.Getter;

/**
 * @Auther: hl
 * @Date: 2018/9/6 12:38
 * @Description: 用户注册枚举
 * @Modified By:
 * @Version 1.0
 */
//@Getter
public enum QueueEnum {
    USER_REGISTER("user.register.queue", "user.register");
    /**
     * 队列名称
     */
    private String name;
    /**
     * 队列路由键
     */
    private String routingKey;

    QueueEnum(String name, String routingKey) {
        this.name = name;
        this.routingKey = routingKey;
    }

    public String getName() {
        return name;
    }

    public String getRoutingKey() {
        return routingKey;
    }
}

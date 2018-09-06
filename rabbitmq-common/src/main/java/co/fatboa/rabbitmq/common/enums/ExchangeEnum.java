package co.fatboa.rabbitmq.common.enums;


import lombok.Getter;

/**
 * @Auther: hl
 * @Date: 2018/9/6 11:43
 * @Description: 用户注册交换配置枚举
 * @Modified By:
 * @Version 1.0
 */
//@Getter
public enum ExchangeEnum {
    USER_REGISTER("user.register.topic.exchange");
    private String value;

    ExchangeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

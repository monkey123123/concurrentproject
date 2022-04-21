package me.monkey.demo.pattern.strategy2;

import org.apache.commons.lang3.StringUtils;

/**
 * 2  * 支付方式枚举对象
 * 3  * code -> 支付方式别名
 * 4  * beanName -> 实例的名称
 * 5  *
 * 6  * @author JinXing
 * 7  * @date 2019/7/12 14:40
 */
public enum PaymentEnum {

    /**
     * 支付方式
     */
    ALI_PAY("ali_pay", AliPayStrategy.class.getSimpleName()),
    WX_PAY("WX_PAY", WxPayStrategy.class.getSimpleName()),
    CARD_PAY("card_pay", CardPayStrategy.class.getSimpleName()),

    ;

    /**
     * 枚举定义+描述
     */
    private String code;
    private String beanName;

    PaymentEnum(String code, String beanName) {
        this.code = code;
        this.beanName = StringUtils.isNotEmpty(beanName) ? beanName.toLowerCase() : null;
    }


    /**
     * 根据code获取对应的枚举对象
     */
    public static PaymentEnum getEnum(String code) {
        PaymentEnum[] values = PaymentEnum.values();
        if (null != code && values.length > 0) {
            for (PaymentEnum value : values) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
        }
        return null;
    }

    /**
     * 该code在枚举列表code属性是否存在
     */
    public static boolean containsCode(String code) {
        PaymentEnum anEnum = getEnum(code);
        return anEnum != null;
    }

    /**
     * 判断code与枚举中的code是否相同
     */
    public static boolean equals(String code, PaymentEnum calendarSourceEnum) {
        return calendarSourceEnum.code.equals(code);
    }


    public String getCode() {
        return code;
    }

    public String getBeanName() {
        return beanName;
    }
}
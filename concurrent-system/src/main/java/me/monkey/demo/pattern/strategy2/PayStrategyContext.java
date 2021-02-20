package me.monkey.demo.pattern.strategy2;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 支付策略上下文
 *
 * @author JinXing
 * @date 2019/7/12 14:39
 */
@Component
public class PayStrategyContext {
    /**
     * 策略实例集合
     */
    private
    ConcurrentHashMap<String, PayStrategy> strategyMap = new ConcurrentHashMap<>(20);

    /**
     * 注入策略实例 * 如果使用的是构造器注入，可能会有多个参数注入进来。
     * * * 如果使用的是field反射注入
     * * * 如果使用的是setter方法注入，那么你将不能将属性设置为final。
     * * * @param strategyMap * 注意注入类型要是Map基础类型
     */
    @Autowired
    public PayStrategyContext(Map<String, PayStrategy> strategyMap) { //清空集合数据
        this.strategyMap.clear();
        if (!CollectionUtils.isEmpty(strategyMap)) {
            strategyMap.forEach((beanName, payStrategy) -> {
                if (StringUtils.isEmpty(beanName) || payStrategy == null) {
                    return;
                }
                this.strategyMap.put(beanName.toLowerCase(), payStrategy);
            });
        }
    }

    /**
     * 选择支付方式 * 支付宝、微信、银行卡
     * * * @param paymentEnums
     * * * @return RemoteResult
     */
    RemoteResult<String> toPayHtml(PaymentEnum paymentEnum) {
        if (CollectionUtils.isEmpty(strategyMap)) {
            return new RemoteResult<String>().error("策略实例集合初始化失败，请检查是否正确注入！");
        }
        return this.strategyMap.get(paymentEnum.getBeanName()).toPayHtml();
    }
}
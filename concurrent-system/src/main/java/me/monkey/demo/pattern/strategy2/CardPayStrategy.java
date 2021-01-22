package me.monkey.demo.pattern.strategy2;

import org.springframework.stereotype.Service;

/**
 * 2  * 银行卡支付
 * 3  * @author JinXing  4  * @date 2019/7/12 14:36  5
 */
@Service
public class CardPayStrategy implements PayStrategy {
    @Override
    public RemoteResult<String> toPayHtml() {
        System.out.println("现在采用的支付方式为：银行卡支付......");
        return null;
    }
}
package me.monkey.demo.pattern.strategy2;

/**
 * 2  * 支付策略接口
 * 3  * @author JinXing  4  * @date 2019/7/12 13:58  5
 */
public interface PayStrategy {
    /**
     * 11  * 12  * 选择支付方式 13  * 支付宝 14  * 微信 15  * 银行卡 16  * @return RemoteResult 17
     */

    RemoteResult<String> toPayHtml();
}
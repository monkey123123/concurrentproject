package me.monkey.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.isAlreadyRouted;

//简单的过滤器日志过滤器，用于打印出每次请求所花费的时间
//全局过滤器只需要用@Component注解即可，不需要特殊配置
//@Component
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI requestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);
        final String scheme = requestUrl.getScheme();
        if (isAlreadyRouted(exchange) || "lb".equals(scheme)
                || "http".equals(scheme) || "https".equals(scheme)
                || "ws".equals(scheme) || "wss".equals(scheme)) {
            return chain.filter(exchange);
        }
        log.info("请求的url为{},协议为{}",requestUrl,scheme);
//        log.info("custom global filter");
        System.out.println("test--------0000----------");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
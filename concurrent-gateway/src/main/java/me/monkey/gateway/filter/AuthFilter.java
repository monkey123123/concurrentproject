package me.monkey.gateway.filter;

import java.nio.charset.StandardCharsets;

//import org.gateway.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSON;

import reactor.core.publisher.Mono;

/**
 * AuthFilter
 */
//@Component
public class AuthFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("-------0000---------------------  " + exchange);
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        String uri = serverHttpRequest.getURI().getPath();

        //  检查白名单（配置）
        if (uri.indexOf("/api/auth/login") >= 0) {
            return chain.filter(exchange);
        }
        String xChannelKey = serverHttpRequest.getHeaders().getFirst("X-Channel-Key");
        if (StringUtils.isBlank(xChannelKey)) {
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            return serverHttpResponse.setComplete();
        }

        String xAuthorization = serverHttpRequest.getHeaders().getFirst("X-Authorization");
        if (StringUtils.isBlank(xAuthorization)) {
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            return serverHttpResponse.setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
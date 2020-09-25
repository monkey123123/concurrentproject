package me.monkey.gateway.fallback;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.BlockResponse;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackProvider;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class MyBlockFallbackProvider implements ZuulBlockFallbackProvider {
    @Override
    public String getRoute() {
        // 对应的route或API group。
        return "default_path_to_httpbin2222";
    }

    @Override
    public BlockResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof BlockException) { // AHAS流控、降级、系统保护异常。
            return new BlockResponse(429, "Blocked by AHAS Sentinel0000", route);
        } else {
            return new BlockResponse(500, "System Error1111", route);
        }
    }
}

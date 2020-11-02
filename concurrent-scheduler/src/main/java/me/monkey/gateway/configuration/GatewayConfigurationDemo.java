package me.monkey.gateway.configuration;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.*;

/**
* Gateway Sentinel 配置
* @author Jeckxu
*/
/*
增加一个GatewayConfiguration 类，用于配置Gateway限流要用到的类，
目前是手动配置的方式，后面肯定是可以通过注解启用，配置文件中指定限流规则的方式来使用，
当然这部分工作会交给Spring Cloud Alibaba来做，后面肯定会发新版本的，大家耐心等待就行了。
 */
//@Configuration
public class GatewayConfigurationDemo {

   private final List<ViewResolver> viewResolvers;
   private final ServerCodecConfigurer serverCodecConfigurer;

   public GatewayConfigurationDemo(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                   ServerCodecConfigurer serverCodecConfigurer) {
       this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
       this.serverCodecConfigurer = serverCodecConfigurer;
   }

   @Bean
   @Order(Ordered.HIGHEST_PRECEDENCE)
   public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
       // Register the block exception handler for Spring Cloud Gateway.
       return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
   }

   @Bean
   @Order(Ordered.HIGHEST_PRECEDENCE)
   public GlobalFilter sentinelGatewayFilter() {
       return new SentinelGatewayFilter();
   }

    @PostConstruct
    public void doInit() {
//        initGatewayRules();
        initFlowQpsRule();
//        initDegradeRule();
    }

    /**
     * 配置限流规则
     */
    private void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("default_path_to_httpbin2222")
                .setCount(1) // 限流阈值
                .setIntervalSec(5) // 统计时间窗口，单位是秒，默认是 1 秒
        );
        GatewayRuleManager.loadRules(rules);
    }
    //限流规则
    private void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule("default_path_to_httpbin2222");
        // set limit qps to 20
        rule.setCount(0);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("concurrent-gateway");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
    //熔断降级规则
    private void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource("0default_path_to_httpbin2222");
        // set threshold RT, 10 ms
        rule.setCount(1);
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        rule.setTimeWindow(10);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

}
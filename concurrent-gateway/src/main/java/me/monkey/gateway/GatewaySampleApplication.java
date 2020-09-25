/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.monkey.gateway;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import me.monkey.gateway.configuration.UriConfiguration;
import me.monkey.gateway.filter.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author Spencer Gibb
 */

/**
 * @EnableCircuitBreaker - 开启断路器。就是开启hystrix服务容错能力。
 * 当应用启用Hystrix服务容错的时候，必须增加的一个注解。
 */
@EnableConfigurationProperties(UriConfiguration.class)
//@SpringBootConfiguration
@SpringBootApplication
//@EnableAutoConfiguration
@EnableDiscoveryClient
//@Import(AdditionalRoutes.class)
public class GatewaySampleApplication {

    public static final String HELLO_FROM_FAKE_ACTUATOR_METRICS_GATEWAY_REQUESTS = "hello from fake /actuator/metrics/gateway.requests";


    public static void main(String[] args) {
        SpringApplication.run(GatewaySampleApplication.class, args);
    }

    //注册自定义的filter，测试发现未生效
//    @Bean
//    public LogGatewayFilterFactory logGatewayFilterFactory() {
//        return new LogGatewayFilterFactory();
//    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
        //@formatter:off
        // String uri = "lb://serviceName";
        // String uri = "http://localhost:9080";
        String uri = uriConfiguration.getUri();

        return builder.routes()
                .route("test_two_filter", r -> r.path("/twofilter")
                        .filters(
                                f -> f.filter(new TestFilter())
                                      .filter(new TestFilter())
                        )
                        .uri(uri)
                )
                .route("rewrite_empty_response", r -> r.path("/")
                        .filters(f -> f
                                .addResponseHeader("X-TestHeader", "rewrite_empty_response")
                                .modifyResponseBody(String.class, String.class,
                                        (exchange, s) -> {
                                            return Mono.just("Error!YouCannotAccessThisAddress!");
                                        })
                        ).uri(uri)
                )
                .route("default_path_to_httpbin1111",
                    r -> r.path("/")
                        .filters(
                                f -> f.filter(
                                        new AuthFilter()
                                )
                        )
                        .uri(uri)
                )
/*
                .route("default_path_to_httpbin2222",
                        r -> r.path("/api/**")
                                .filters(f -> f.filter(new AuthFilter())   // .prefixPath("/") //prefixPath就是在uri最前面加上指定字符串
                                .addResponseHeader("X-TestHeader", "foobar2222"))
                                .uri(uri)
                )
*/

                .route("default_path_to_websocket1111",
                        r -> r.path("/websocket/**")
                                .filters(f -> f.filter(new AuthFilter()))
                                .uri(uri)
                )

        /*      在 Application.java 中，添加类级别注释 @RestController，然后将以下 @RequestMapping 添加到该类中。
        src/main/java/gateway/Application.java
        @RequestMapping("/fallback")
        public Mono<String> fallback() {
            return Mono.just("fallback");
        }
        */
                /*
                .route(p -> p
                        .host("*.hystrix.com")
                        .filters(f -> f.hystrix(config -> config
                                .setName("mycmd")
                                .setFallbackUri("forward:/fallback")))
                        .uri(uri))
                .route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
                        .filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
                        .uri(uri))
                */
                .route(r -> r.host("**.abc.org").and().path("/anything/png")
                        .filters(f ->
                                f.prefixPath("/httpbin")
                                        .addResponseHeader("X-TestHeader", "foobar"))
                        .uri(uri)
                )
                .route("read_body_pred", r -> r.host("*.readbody.org")
                        .and().readBody(String.class,
                                s -> s.trim().equalsIgnoreCase("hi"))
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "read_body_pred")
                        ).uri(uri)
                )
                .route("rewrite_request_obj", r -> r.host("*.rewriterequestobj.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "rewrite_request")
                                .modifyRequestBody(String.class, Hello.class, MediaType.APPLICATION_JSON_VALUE,
                                        (exchange, s) -> {
                                            return Mono.just(new Hello(s.toUpperCase()));
                                        })
                        ).uri(uri)
                )
                .route("rewrite_request_upper", r -> r.host("*.rewriterequestupper.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "rewrite_request_upper")
                                .modifyRequestBody(String.class, String.class,
                                        (exchange, s) -> {
                                            return Mono.just(s.toUpperCase() + s.toUpperCase());
                                        })
                        ).uri(uri)
                )
                .route("rewrite_response_upper", r -> r.host("*.rewriteresponseupper.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "rewrite_response_upper")
                                .modifyResponseBody(String.class, String.class,
                                        (exchange, s) -> {
                                            return Mono.just(s.toUpperCase());
                                        })
                        ).uri(uri)
                )
                .route("rewrite_empty_response", r -> r.host("*.rewriteemptyresponse.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "rewrite_empty_response")
                                .modifyResponseBody(String.class, String.class,
                                        (exchange, s) -> {
                                            if (s == null) {
                                                return Mono.just("emptybody");
                                            }
                                            return Mono.just(s.toUpperCase());
                                        })

                        ).uri(uri)
                )
                .route("rewrite_response_fail_supplier", r -> r.host("*.rewriteresponsewithfailsupplier.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "rewrite_response_fail_supplier")
                                .modifyResponseBody(String.class, String.class,
                                        (exchange, s) -> {
                                            if (s == null) {
                                                return Mono.error(new IllegalArgumentException("this should not happen"));
                                            }
                                            return Mono.just(s.toUpperCase());
                                        })
                        ).uri(uri)
                )
                .route("rewrite_response_obj", r -> r.host("*.rewriteresponseobj.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "rewrite_response_obj")
                                .modifyResponseBody(Map.class, String.class, MediaType.TEXT_PLAIN_VALUE,
                                        (exchange, map) -> {
                                            Object data = map.get("data");
                                            return Mono.just(data.toString());
                                        })
                                .setResponseHeader("Content-Type", MediaType.TEXT_PLAIN_VALUE)
                        ).uri(uri)
                )
                .route(r -> r.path("/image/webp")
                        .filters(f ->
                                f.prefixPath("/httpbin")
                                        .addResponseHeader("X-AnotherHeader", "baz"))
                        .uri(uri)
                )
                .route(r -> r.order(-1)
                        .host("**.throttle.org").and().path("/get")
                        .filters(f -> f.prefixPath("/httpbin")
                                .filter(new ThrottleGatewayFilter()
                                        .setCapacity(1)
                                        .setRefillTokens(1)
                                        .setRefillPeriod(10)
                                        .setRefillUnit(TimeUnit.SECONDS)))
                        .uri(uri)
                )
                .build();
        //@formatter:on
    }

    @Bean
    public RouterFunction<ServerResponse> testFunRouterFunction() {
        RouterFunction<ServerResponse> route = RouterFunctions.route(
                RequestPredicates.path("/testfun"),
                request -> ServerResponse.ok().body(BodyInserters.fromValue("hello")));
        return route;
    }

    @Bean
    public RouterFunction<ServerResponse> testWhenMetricPathIsNotMeet() {
        RouterFunction<ServerResponse> route = RouterFunctions.route(
                RequestPredicates.path("/actuator/metrics/gateway.requests"),
                request -> ServerResponse.ok().body(BodyInserters
                        .fromValue(HELLO_FROM_FAKE_ACTUATOR_METRICS_GATEWAY_REQUESTS)));
        return route;
    }

    static class Hello {

        String message;

        Hello() {
        }

        Hello(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    @RequestMapping(value = "/fallbackcontroller")
    public Map<String, String> fallBackController() {
        Map<String, String> res = new HashMap();
        res.put("code", "-100");
        res.put("data", "service not available");
        return res;
    }



}

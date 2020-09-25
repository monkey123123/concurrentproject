package me.monkey.gateway.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



/**
 * @Param uri 代表http请求路径
 */
@Data
@ConfigurationProperties(prefix = "api-application")
@Component
public class UriConfiguration {
//  lb:application
//  @Value("${api-application.uri:http://httpbin.org:80}")
  String uri;

}
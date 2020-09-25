package me.monkey.gateway.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Param uri 代表zookeeper地址
 * @Param path 代表限流规则保存路径
 */
@Data
@Component
public class ZookeeperConfiguration {
    @Value("${spring.cloud.zookeeper.connect-string}")
    private String uriTemp;
    @Value("${zookeeper.path}")
    private String pathTemp;

    public static String uri;
    public static String path;

    @PostConstruct
    private void init(){
        ZookeeperConfiguration.uri = uriTemp;
        ZookeeperConfiguration.path = pathTemp;
        System.out.println("ZookeeperConfiguration init---------------------");
    }

}
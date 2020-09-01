package me.monkey;

import com.alibaba.fastjson.parser.ParserConfig;
import me.monkey.util.SnmpPref;
import me.monkey.util.ResponseFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 */
//@Configuration
//@EnableAutoConfiguration
//@EnableDiscoveryClient
//@EnableFeignClients
//@EnableCircuitBreaker
//@EnableHystrix//add by allen

//@ServletComponentScan
//@EnableMongoRepositories("me.monkey.demo.schedule")
//@EnableScheduling
@EnableAsync
@SpringBootApplication
//@EnableAutoConfiguration(exclude = ElasticsearchAutoConfiguration.class)
@EnableTransactionManagement
@MapperScan("me.monkey.dao")
//@EnableMongoRepositories("me.monkey.modules.system.repository.mongodb")
@EnableConfigurationProperties({ SnmpPref.class })
//@PropertySource(value = {"classpath:application.yml","file:/D:/test.yml"}, encoding = "utf-8", factory = ResponseFactory.class)
@PropertySource(value = {"file:/D:/test.yml"}, encoding = "utf-8", factory = ResponseFactory.class)
public class App {
    public static void main(String[] args) {
        ParserConfig.getGlobalInstance().setSafeMode(true);
        SpringApplication.run(App.class, args);
    }

}

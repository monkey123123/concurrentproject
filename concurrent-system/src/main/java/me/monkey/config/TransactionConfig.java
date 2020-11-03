package me.monkey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/*
暂时不用，先注掉
 */
//@Configuration
public class TransactionConfig {
    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory factory){
        return new MongoTransactionManager(factory);
    }
}
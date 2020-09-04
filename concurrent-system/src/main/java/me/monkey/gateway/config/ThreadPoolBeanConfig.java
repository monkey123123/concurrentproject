package me.monkey.gateway.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolBeanConfig {


    @Value("${task.pool.core-pool-size}")
    private Integer corePoolSize;
    @Value("${task.pool.max-pool-size}")
    private Integer maxPoolSize;
    @Value("${task.pool.keep-alive-seconds}")
    private Integer keepAliveSeconds;
    @Value("${task.pool.queue-capacity}")
    private Integer queueCapacity;
    @Value("${task.pool.core-pool-size}")
    private String namePrefix = "pool-task-";
    // 上述属性可以通过@Value来读取配置值

    @Bean(name = "asyncServiceTaskExecutor",destroyMethod = "shutdown")
    public TaskExecutor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 设置队列容量
        executor.setQueueCapacity(queueCapacity);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 设置默认线程名称
        executor.setThreadNamePrefix(namePrefix);
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }
}

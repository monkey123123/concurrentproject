package me.monkey.demo.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadPoolUtil {
    private static int corePoolSize = 8;
    private static int maximumPoolSize = 10;
    private static long keepAliveTime = 60L;
    private static TimeUnit unit = TimeUnit.SECONDS;
    private static int capacity = 1024;
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("saltevent-thread-pool-%d").build();
    private static final ExecutorService executorService = new ThreadPoolExecutor(corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            unit,
            new LinkedBlockingQueue<>(capacity),
            namedThreadFactory,
            new ThreadPoolExecutor.CallerRunsPolicy());
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            unit,
            new LinkedBlockingQueue<>(capacity),
            namedThreadFactory,
            new ThreadPoolExecutor.CallerRunsPolicy());

    private ThreadPoolUtil() {

    }
    /*
     * 用时调此处
     */
    public static ExecutorService getExecutorService() {
        return executorService;
    }
    /*
     * 用时调此处
     */
    public static ThreadPoolExecutor getExecutor() {
        return executor;
    }
}
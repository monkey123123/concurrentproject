package me.monkey.gateway.demo.saltstack;

import com.alibaba.fastjson.JSONObject;
import com.suse.salt.netapi.AuthModule;
import com.suse.salt.netapi.client.SaltClient;
import com.suse.salt.netapi.client.impl.HttpAsyncClientImpl;
import com.suse.salt.netapi.datatypes.Event;
import com.suse.salt.netapi.datatypes.Token;
import com.suse.salt.netapi.event.EventListener;
import com.suse.salt.netapi.event.WebSocketEventStream;
import com.suse.salt.netapi.utils.HttpClientUtils;
import me.monkey.gateway.demo.threadpool.ThreadPoolUtil;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.net.URI;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class Events {

    private static final String SALT_API_URL = "http://127.0.0.1:8000";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static AtomicLong atomicLong = new AtomicLong();
    private static AtomicLong atomicLongRun = new AtomicLong();

    public static void run(){
        System.out.println("atomicLongRun.incrementAndGet(): "+atomicLongRun.incrementAndGet());
        System.out.println("----run()----Thread.currentThread().getId()-------"+Thread.currentThread().getId());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        // Init client and set the timeout to infinite
        SaltClient client = new SaltClient(URI.create(SALT_API_URL),
                new HttpAsyncClientImpl(HttpClientUtils.defaultClient()));

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(0)
                .setConnectTimeout(0)
                .setSocketTimeout(0)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        HttpAsyncClientBuilder httpClientBuilder = HttpAsyncClients.custom();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);

        CloseableHttpAsyncClient asyncHttpClient = httpClientBuilder.build();
        asyncHttpClient.start();

        try {
            // Get a login token
            Token token = client.login(USER, PASSWORD, AuthModule.AUTO).toCompletableFuture().join();
            System.out.println("Token: " + token.getToken());

            EventListener eventListener = new EventListener() {
                @Override
                public void notify(Event event) {
                    System.out.println("atomicLong.incrementAndGet(): "+atomicLong.incrementAndGet());
                    System.out.println("----notify()----Thread.currentThread().getId()-------"+Thread.currentThread().getId());
                    System.out.println(JSONObject.toJSONString(event));
//                    Future<?> submit = ThreadPoolUtil.getExecutorService().submit(() -> run());
                    Future<?> submit = ThreadPoolUtil.getExecutor().submit(()->run());
                    System.out.println("remainingCapacity: "+ThreadPoolUtil.getExecutor().getQueue().remainingCapacity());
                    System.out.println(JSONObject.toJSONString(ThreadPoolUtil.getExecutorService()));
                }

//                @SneakyThrows
                @Override
                public void eventStreamClosed(int code, String phrase) {
                    System.out.println("Event eventStreamClosed code: " + code);
                    System.out.println("Event eventStreamClosed phrase: " + phrase);
                }
            };

            // Init the event stream with a basic listener implementation
            WebSocketEventStream eventStream = client.events(
                    token, 0, 0, 0, eventListener
                    /*new EventListener() {
                        @Override
                        public void notify(Event e) {
                            System.out.println(JSONObject.toJSONString(e));
//                            System.out.println("Tag  -> " + e.getTag());
//                            System.out.println("Data -> " + e.getData());
                        }

                        @Override
                        public void eventStreamClosed(int code, String phrase) {
                            System.out.println("Event eventStreamClosed code: " + code);
                            System.out.println("Event eventStreamClosed phrase: " + phrase);
                        }
                    }*/);

            // Wait for events and close the event stream after 30 seconds
            System.out.println("-- Waiting for events --");
            Thread.sleep(1200000);
            eventStream.close();
            System.out.println("-- Stop --");
//            eventListener.eventStreamClosed();
            client.logout();
            ThreadPoolUtil.getExecutorService().shutdown();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
package me.monkey.gateway.demo.threadpool;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class FiberDemo2 {
    static AtomicInteger at = new AtomicInteger();

    public static void main() {
        CloseableHttpResponse response = null;
        try {
            //1.获得一个httpclient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //2.生成一个get请求
            HttpGet httpget = new HttpGet("http://localhost:8080/api/test");
            //3.执行get请求并返回结果
            response = httpclient.execute(httpget);
            System.out.println(Thread.currentThread().getId()
                    +"-----------"+at.getAndIncrement()+"----at:---- "+response);
            //4.处理结果
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        CountDownLatch count  = new CountDownLatch(num);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        IntStream.range(0,num).forEach(i-> executorService.submit(() -> {

                main();
//                TimeUnit.SECONDS.sleep(1);

//            catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
            count.countDown();
        }));
        count.await();
        stopWatch.stop();
        System.out.println("结束了: " + stopWatch.prettyPrint());
    }

    public static void main3(String[] args) throws Exception{
        CountDownLatch count  = new CountDownLatch(10000);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0,10000).forEach(i-> executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) { }
            count.countDown();
        }));
        count.await();
        stopWatch.stop();
        System.out.println("结束了: " + stopWatch.prettyPrint());
    }
}

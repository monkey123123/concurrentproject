package me.monkey.demo.nio;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.util.concurrent.Future;

public class AsyncClientHttpExchange {
    public static void main(final String[] args) throws Exception {
        // 默认的配置
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
             httpclient.start();
             HttpGet request = new HttpGet("http://www.apache.org/");
             Future<HttpResponse> future = httpclient.execute(request, null);
             // 获取结果
             HttpResponse response = future.get();
             System.out.println("Response: " + response.getStatusLine());
             System.out.println("Shutting down");
        } finally {
             httpclient.close();
            System.out.println("------finally-----");
        }
        System.out.println("Done");
    }
}
//原文链接：https://blog.csdn.net/new03/article/details/84827098
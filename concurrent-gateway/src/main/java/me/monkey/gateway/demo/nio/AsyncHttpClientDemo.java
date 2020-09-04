package me.monkey.gateway.demo.nio;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class AsyncHttpClientDemo {
    public static void main(String[] args) {

        ConnectingIOReactor ioReactor = null;
        try {
            ioReactor = new DefaultConnectingIOReactor();
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
        PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(ioReactor);
        cm.setMaxTotal(10);

        HttpAsyncClientBuilder builder = HttpAsyncClients.custom().setConnectionManager(cm);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(10000)
                .setSocketTimeout(500000)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        CloseableHttpAsyncClient httpAsyncClient = builder.build();
//        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom().setConnectionManager(cm).build();
        httpAsyncClient.start();
        String[] urisToGet = {
                "http://www.chinaso.com/",
                "http://www.so.com/",
                "http://www.qq.com/",
        };

        final CountDownLatch latch = new CountDownLatch(urisToGet.length);
        for (final String uri: urisToGet) {
            final HttpGet httpget = new HttpGet(uri);
            httpAsyncClient.execute(httpget, new FutureCallback<HttpResponse>() {
                public void completed(final HttpResponse response) {
                    latch.countDown();
                    System.out.println(httpget.getRequestLine() + "->" + response.getStatusLine());
                }
                public void failed(final Exception ex) {
                    latch.countDown();
                    System.out.println(httpget.getRequestLine() + "->" + ex);
                }
                public void cancelled() {
                    latch.countDown();
                    System.out.println(httpget.getRequestLine() + " cancelled");
                }

            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            cm.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpAsyncClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

package me.monkey;

import com.alibaba.fastjson.parser.ParserConfig;
import me.monkey.util.YamlData;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegerTest {

    int count = 10000;
    CountDownLatch latch = new CountDownLatch(1);
    @Autowired
    private YamlData yamlData;

    @Test
    public void test1111() {
            System.out.println("666555:" + yamlData.getName()); // 666555:张三
            System.out.println("666555:" + YamlData.getName()); // 666555:张三
    }

    @Test
    public void checkFastjsonAutoType() {
        boolean autoTypeSupport = ParserConfig.getGlobalInstance().isAutoTypeSupport();
        System.out.println(autoTypeSupport);
    }

    /*
        测试结果：
        qps只能到几百，到1000就会有很多问题。
     */
    @Test
    public void compareInteger() throws IOException {
//1.获得一个httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
//2.生成一个get请求
        HttpGet httpget = new HttpGet("http://localhost/");
//3.执行get请求并返回结果
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            //4.处理结果
        } finally {
            response.close();
        }

    }

    @Test
    public void testListRemove() {

        long start = System.currentTimeMillis();
        System.out.println("------1111-----" + start);

        Integer integerA = 128;
        Integer integerB = 128;
        if (integerA == integerB) {

        }
        boolean flag = integerA == integerB;
        System.out.println(flag);
        long end = System.currentTimeMillis();
        System.out.println("------4444----------------" + (end - start));

    }


}


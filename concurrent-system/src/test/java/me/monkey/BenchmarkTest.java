package me.monkey;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import me.monkey.dao.IPhoneMessageDao;
import me.monkey.pojo.PhoneMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BenchmarkTest {
    @Autowired
    private IPhoneMessageDao messageDao;
    int count = 10000;
    CountDownLatch latch = new CountDownLatch(1);
    /*
        测试结果：
        qps只能到几百，到1000就会有很多问题。
        但是db里的数据太少。应通过批量增加一批数据来再次测试才有意义。
     */
    @Test
    public void mysqlSelect0(){

        long start = System.currentTimeMillis();
        System.out.println("------1111-----"+start);
        HashMap map = new HashMap();
        map.put("needAck",1);
        List<PhoneMessage> list = messageDao.selectPhoneMessage(map);
        System.out.println(JSONObject.toJSONString(list));

        long end = System.currentTimeMillis();
        System.out.println("------4444----------------"+(end - start));

    }
    @Test
    public void mysqlSelect(){
        for(int i=0;i<count;i++){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    HashMap map = new HashMap();
                    map.put("needAck",1);
                    List<PhoneMessage> list = messageDao.selectPhoneMessage(map);
                    System.out.println(JSONObject.toJSONString(list));
                }
            }).start();
            System.out.println(i+"------0000-----");

            //latch.countDown();
        }
        long start = System.currentTimeMillis();
        System.out.println("------1111-----"+start);
        try {
            latch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("------4444----------------"+(end - start));
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void test(){
        HashMap map = new HashMap();
        map.put("needAck",1);
        List<PhoneMessage> list = messageDao.selectPhoneMessage(map);
        System.out.println(JSONObject.toJSONString(list));



    }
}


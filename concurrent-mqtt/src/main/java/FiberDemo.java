import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.SuspendableCallable;
import co.paralleluniverse.strands.concurrent.CountDownLatch;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class FiberDemo {
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
        //使用阻塞队列来获取结果。
        LinkedBlockingQueue<Fiber<Integer>> fiberQueue = new LinkedBlockingQueue<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 1; i++) {
            int finalI = i;
            //这里的Fiber有点像Callable,可以返回数据
            Fiber<Integer> fiber = new Fiber<>((SuspendableCallable<Integer>) () -> {
                //这里用于测试内存占用量
                main();
//                Fiber.sleep(10000);
                System.out.println("in-" + finalI + "-" + LocalDateTime.now().format(formatter));
                return finalI;
            });
            //开始执行
            fiber.start();
            //加入队列
            fiberQueue.add(fiber);
        }
        while (true) {
            //阻塞
            Fiber<Integer> fiber = null;
            try {
                fiber = fiberQueue.take();
                System.out.println("out-" + fiber.get() + "-" + LocalDateTime.now().format(formatter));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main2(String[] args) throws Exception {
        CountDownLatch count = new CountDownLatch(10000);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        IntStream.range(0, 10000).forEach(i -> new Fiber() {
            @Override
            protected String run() throws SuspendExecution, InterruptedException {
                Strand.sleep(1000);
                count.countDown();
//                System.out.println(i);
                return "aa";
            }
        }.start());
        count.await();
        stopWatch.stop();
        System.out.println("结束了: " + stopWatch.prettyPrint());
    }

    public static void main3(String[] args) {
        //使用阻塞队列来获取结果。
        LinkedBlockingQueue<Fiber<Integer>> fiberQueue = new LinkedBlockingQueue<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 1000000; i++) {
            int finalI = i;
            //这里的Fiber有点像Callable,可以返回数据
            Fiber<Integer> fiber = new Fiber<>((SuspendableCallable<Integer>) () -> {
                //这里用于测试内存占用量
                Fiber.sleep(10000);
                System.out.println("in-" + finalI + "-" + LocalDateTime.now().format(formatter));
                return finalI;
            });
            //开始执行
            fiber.start();
            //加入队列
            fiberQueue.add(fiber);
        }
        while (true) {
            //阻塞
            Fiber<Integer> fiber = null;
            try {
                fiber = fiberQueue.take();
                System.out.println("out-" + fiber.get() + "-" + LocalDateTime.now().format(formatter));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

}

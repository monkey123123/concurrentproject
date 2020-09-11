package me.monkey;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.strands.SuspendableCallable;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

public class IntegerCompareTest {

    int count = 10000;
    CountDownLatch latch = new CountDownLatch(1);


    @Test
    public void testQusar() throws InterruptedException, ExecutionException {
        //使用阻塞队列来获取结果。
        LinkedBlockingQueue<Fiber<Integer>> fiberQueue = new LinkedBlockingQueue<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 1000000; i++) {
            int finalI = i;
            //这里的Fiber有点像Callable,可以返回数据
            Fiber<Integer> fiber = new Fiber<>((SuspendableCallable<Integer>) () -> {
                //这里用于测试内存占用量
                Fiber.sleep(100000);
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
            Fiber<Integer> fiber = fiberQueue.take();
            System.out.println("out-" + fiber.get() + "-" + LocalDateTime.now().format(formatter));
        }

    }

    @Test
    public void testIntegerCompare() {
        Integer integerA = 128;
        Integer integerB = 128;
        System.out.println(integerA == integerB);//false
        System.out.println(Objects.equals(integerA, integerB));//true
    }

    @Test
    public void testListRemove() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("1".equals(item)) {
                iterator.remove();
            }
        }

    }

    /**
     * 除了删除倒数第二个错误不会异常，删除其他元素均会异常。
     * 从源码层面揭露该异常的原因。首先发现Java的for循环，就是将List对象遍历托管给Iterator，你如果要对list进行增删操作，都必须经过Iterator，
     * 否则Iterator遍历时会乱，所以直接对list进行删除时，Iterator会抛出ConcurrentModificationException异常。
     * 查阅源码，发现：
     * 　　1.modCount 时List从new开始，被修改的次数。当List调用Remove等方法时，modCount++
     * 　　2.expectedModCount是指Iterator现在期望这个list被修改的次数是多少次。是在Iterator初始化的时候将modCount 的值赋给了expectedModCount
     * 这就解释了为什么会报上述异常：
     * 　　1.modCount 会随着调用List.remove方法而自动增减，而expectedModCount则不会变化，就导致modCount != expectedModCount。
     * 　　2.在删除倒数第二个元素后，cursor=size-1，此时size=size-1，导致hasNext方法认为遍历结束。
     * 查阅源码，发现iterator也有一个remove方法如下，其中有一个重要的操作为expectedModCount = modCount;这样就保证了两者的相等。
     */
    @Test
    public void testListRemoveError() {

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }

    }

    @Test
    public void testListRemoveError2() {
        List<Integer> listA = new ArrayList<>();
        listA.add(1);
        listA.add(2);
        listA.add(3);
        listA.add(4);
        listA.add(5);
        listA.add(6);

        for (Integer a : listA) {
            System.out.println(a);
            if (a == 2) {
                listA.remove(2);
            }
        }

    }


}


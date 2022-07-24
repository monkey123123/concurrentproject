package me.monkey.demo.queue.delayqueue.linkedblockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue queue = new LinkedBlockingQueue(2);
        queue.put("1");
        queue.put("2");
        System.out.println(1111);
        queue.put("3");//TODO 如果队列满了，再放元素会一直阻塞，无法执行。这在生产上存在极大风险，要注意。
        System.out.println(2222);

    }
}

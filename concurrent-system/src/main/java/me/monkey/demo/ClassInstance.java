package me.monkey.demo;

//import org.springframework.data.mongodb.core.aggregation.ObjectOperators;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ClassInstance {


    public static void main(String[] args) {

        Integer integerA = 127;
        Integer integerB = 127;
        System.out.println(integerA == integerB);//true
        Integer integerC = 128;
        Integer integerD = 128;
        System.out.println(integerC == integerD);//true
        System.out.println(Objects.equals(integerC, integerD));//true








        Thread t = Thread.currentThread();
        System.out.println(Thread.currentThread());
        System.out.println(t);

        A a = new A();

        int m = a.NO;
        System.out.println(m);


        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }

        for(int i=0;i<10;i++){
            System.out.println(i);
        }
/*Exception in thread "main" java.util.ConcurrentModificationException
    at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
	at java.util.ArrayList$Itr.next(ArrayList.java:859)
	at me.monkey.demo.ClassInstance.main(ClassInstance.java:17)
 */
        String[] array = new String[list.size()];
        String[] objects = list.toArray(array);
        for (String s : objects) {
            System.out.println(s);
        }

    }
}

class A {
    public static int NO = 1;
}


class TestTask implements Runnable {
    private boolean stop = false;
    private ThreadLocal<SimpleDateFormat> sdfHolder = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    @Override
    public void run() {
        while (!stop) {
            String formatedDateStr = sdfHolder.get().format(new Date());
            System.out.println("formated date str:" + formatedDateStr);
            //may be sleep for a while to avoid high cpu cost
        }
        sdfHolder.remove();
    }

    //something else
}



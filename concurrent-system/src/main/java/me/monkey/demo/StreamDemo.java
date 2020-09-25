package me.monkey.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {
    /**
     * 返回的都是流对象
     * @param args
     */
    public static void main(String[] args) {
//        1、从字符串数组创建出流对象:
//        Stream<String> split = Stream.of(str.split(" "));
//        2、通过流对象的API执行中间操作(filter)，返回的还是流对象：
//        Stream<String> filterStream = split.filter(s -> s.length() > 2);
//        3、通过返回的流对象再执行中间操作(map)，返回的还是流对象：
//        Stream<Integer> integerStream = filterStream.map(s -> s.length());
//        因为中间操作返回的都是流对象，所以我们可以链式调用。
        String str = "my name is 007";

        Stream.of(str.split(" ")).filter(s -> s.length() > 2)
                .map(s -> s.length()).forEach(System.out::println);

//        最终操作返回的不再是Stream对象，调用了最终操作的方法，Stream才会执行。还是以上面的例子为例：

        String str2 = "my name is 008";
        Stream.of(str2.split(" ")).peek(System.out::println).forEach(System.out::println);

        int[] nums = { 1, 2, 3 };
        int sum2 = IntStream.of(nums).parallel().sum();
        System.out.println("结果为：" + sum2);

        List<String> list = new ArrayList<>();
        // 从集合创建
        Stream<String> stream = list.stream();
        Stream<String> stream1 = list.parallelStream();

        // 从数组创建
        IntStream stream2 = Arrays.stream(new int[]{2, 3, 5});

        // 创建数字流
        IntStream intStream = IntStream.of(1, 2, 3);

        // 使用random创建
        IntStream limit = new Random().ints().limit(10);

    }
}

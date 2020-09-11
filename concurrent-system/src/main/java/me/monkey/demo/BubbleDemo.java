package me.monkey.demo;

import java.util.Arrays;

public class BubbleDemo {
    public static void main(String[] args) {
//        BubbleDemo.bubble();
        test();
    }


    public static void test(){

        int[] arr = new int[]{9,4,2,6,7};
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length-1-i; j++)
                if(arr[j]>arr[j+1]){
                    int tmp = arr[i];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            System.out.println(Arrays.toString(arr));
        }
    }







    /*
       应聘者写法,逆冒泡算法
     */
    public static void bubble() {
        int[] array = {7, 2, 5, 4, 9, 6};
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            System.out.println(Arrays.toString(array));
        }
    }
    
    
    /* 冒泡排序算法 */ 


    public static int[] sort(int[] m) {
        int intLenth = m.length;
        /*执行intLenth次*/
        for (int i = 0; i < intLenth; i++)
        {
            /*每执行一次，将最da的数排在后面*/
            for (int j = 0; j < intLenth - i - 1; j++)
            {
                int a = m[j];
                int b = m[j + 1];
                if (a > b){
                    m[j] = b;
                    m[j + 1] = a;
                }
            }
        }
        return m;
    }

}

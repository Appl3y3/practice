package com.appleye.thread;

import java.time.Year;
import java.util.concurrent.*;

/**
 * @author Appleye
 * @createTime 2020/7/14 14:20
 */
public class Demo {
    /**
     * 继承Thread创建线程类
     */
    public static class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("my thread");
        }
    }

    /**
     * 实现Runnable类创建线程类
     */
    public static class MyThread2 implements Runnable {
        @Override
        public void run() {
            System.out.println("my runnable");
        }
    }

    /**
     * 实现Callable接口创建有返回值的线程
     */
    public static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            //模拟计算1秒
            Thread.sleep(1000);
            return 2;
        }
    }




    public static void main(String[] args) {
        MyThread1 myThread = new MyThread1();
//        myThread.start();

        // Java 8 函数式编程，可以省略MyThread类
//        new Thread(() -> System.out.println("匿名内部类")).start();


//        Thread thread = new Thread(new MyThread2());

        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> submit = executorService.submit(task);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }





    }

}

package com.concurrency.www;

import java.util.concurrent.*;

/**
 * 1，Callable的call有返回值，Runnable的run没有返回值
 * 2，call的返回值用Future或FutureTask的get方法获取
 * 3，Future需要ExecutorService来执行；FutureTask直接用Thread就可以
 */
public class CallableTest {

    public static void main(String[] args){
        //FutureTest();
        FutureTaskTest();
    }


    /**
     * 测试Future
     */
    public static void FutureTest(){
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }


    /**
     * test FutureTask
     */
    private static void FutureTaskTest(){


        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");

    }




}


class Task implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程正在计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }
}


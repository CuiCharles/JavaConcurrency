package com.concurrency.www;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

import java.util.concurrent.CountDownLatch;


/**
 * 提供一个倒计时计数器，在倒计时到0时，唤醒另一个线程的等待。
 */
public class CountDownLatchTest {



    private static void test1(){
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();


        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(6000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();


        try {
            System.out.println("等待2个子线程执行完毕...");
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private static void test2(){

        int count = 10;
        final CountDownLatch l = new CountDownLatch(count);
        for(int i = 0;i < count; i++){
            final int index = i+1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.currentThread().sleep(1000L);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("thread " + index + " has finished...");
                    l.countDown();

                }
            }).start();
        }


        try{
            l.await();
        }catch (InterruptedException e){

        }
        System.out.println("now all threads have finished");
    }



    public static void main(String[] args){
        //test1();
        test2();
    }
}

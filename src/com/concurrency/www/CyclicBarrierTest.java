package com.concurrency.www;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 总的来说：CountDownLatch和CyclicBarrier都可以协调不同线程之间的等待，只不过侧重点不同：
 * 1，CountDownLatch一般用于某个线程A等其他几个线程执行完后在执行
 * 2，CyclicBarrier用于多个线程相互等待至某个状态，然后这组线程再同时执行
 *
 * 前者不可重用，后者可重用
 */
public class CyclicBarrierTest {

    public static void main(String[] args){


        CyclicBarrier barrier = new CyclicBarrier(4);
        for(int i = 1; i < 5; i++){
            new Writer(barrier,i*1000).start();
        }

    }



    static class Writer extends Thread{

        private CyclicBarrier cyclicBarrier;
        private long sleepTime;

        public Writer(CyclicBarrier barrier,long time){
            this.cyclicBarrier = barrier;
            this.sleepTime = time;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(this.sleepTime);
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，线程"+Thread.currentThread().getName()+"继续处理其他任务...");
        }
    }

}

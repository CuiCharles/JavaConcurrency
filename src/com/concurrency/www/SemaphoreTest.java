package com.concurrency.www;

import java.util.concurrent.Semaphore;

/**
 * 控制同时访问线程的个数
 *
 * 跟锁的特点类似，一般用于控制对资源访问的权限
 *
 */
public class SemaphoreTest {


    public static void main(String[] args){
        int N = 8;//工人数
        Semaphore semaphore = new Semaphore(5);//机器数

        for(int i = 1; i <= N; i++){
            new Worker(i,semaphore).start();
        }
    }


    static class Worker extends Thread{

        private int num;
        private Semaphore semaphore;
        public Worker (int num, Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try{
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();
            }catch(InterruptedException e){

            }

        }
    }


}

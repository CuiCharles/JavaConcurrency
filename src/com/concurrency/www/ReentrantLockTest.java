package com.concurrency.www;

public class ReentrantLockTest {

    static int sum = 1000;


    static class DepositThread extends Thread{
        @Override
        public void run() {
            sum++;
            System.out.println("add");
        }
    }



    static class WithdrawThread extends Thread{
        @Override
        public void run() {
            sum--;
            System.out.println("mov");
        }
    }






    public static void main (String[] args){

        for(int i = 0; i < 20; i++){
            new DepositThread().start();
            new WithdrawThread().start();
        }


        System.out.println("after run:" + sum);

    }


}

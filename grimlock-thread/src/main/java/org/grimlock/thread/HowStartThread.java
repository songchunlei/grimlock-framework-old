package org.grimlock.thread;

/**
 * @Author:chunlei.song@live.com
 * @Description: 声明线程的方法
 * @Date Create in 10:45 2017-12-29
 * @Modified By:
 */
public class HowStartThread {


    private static class TestThread extends Thread{
        @Override
        public void run() {
            System.out.println("TestThread is runing");
        }
    }

    private static class TestRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("TestRunnable is runing");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new TestThread();
        Thread thread2 = new Thread(new TestRunnable());
        thread1.run();
        thread2.run();
    }


}

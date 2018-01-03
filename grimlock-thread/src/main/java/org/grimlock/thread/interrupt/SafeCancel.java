package org.grimlock.thread.interrupt;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 11:06 2017-12-29
 * @Modified By:
 */
public class SafeCancel {
    private static class TestRunnable implements Runnable{

        private volatile boolean on = true;

        private long i =0;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted())
            {
                i++;
                /**
                 * 这边有阻塞方法，就意味着不会取到on参数，那么on就不会生效
                 */
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("TestRunnable is runing");
        }

        public void Cancel()
        {
            on = false;
            Thread.currentThread().interrupt();
        }
    }
}

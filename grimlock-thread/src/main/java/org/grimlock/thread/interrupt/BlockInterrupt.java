package org.grimlock.thread.interrupt;

/**
 * @Author:chunlei.song@live.com
 * @Description:阻塞处理
 * @Date Create in 11:21 2017-12-29
 * @Modified By:
 */
public class BlockInterrupt {

    private static volatile boolean on = true;

    private static class WhenBlock implements Runnable{

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted())
            {
                //如果遇到阻塞，抛出异常后，中断标志位改成false;
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();//重新设置
                }
                //


            }
        }

        public void Cancel()
        {
            on = false;
            Thread.currentThread().interrupt();
        }
    }
}

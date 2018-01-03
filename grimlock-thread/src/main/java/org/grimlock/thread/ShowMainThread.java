package org.grimlock.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Author:chunlei.song@live.com
 * @Description: 怎么查看主线程
 * @Date Create in 10:31 2017-12-29
 * @Modified By:
 */
public class ShowMainThread {

    public static void main(String[] args) {
        //获取JAVA线程管理接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //1.不考虑监视器
        //2.不考虑琐信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo threadInfo : threadInfos)
        {
            System.out.println(threadInfo.getThreadId()+" "+threadInfo.getThreadName() );
        }
    }


}

package org.grimlock.admin.server.command;

import com.netflix.hystrix.*;

import java.net.URL;

/**
 * @Author:chunlei.song@live.com
 * @Description: 原生写法，设置熔断。每个命令封装很麻烦
 * @Date Create in 17:28 2018-1-2
 * @Modified By:
 */
public class SmsSendCommand extends HystrixCommand<String>{
    public SmsSendCommand() {
        super(Setter
                // 必填项。指定命令分组名，主要意义是用于统计
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("smsGroup"))
                // 依赖名称（如果是服务调用，这里就写具体的接口名， 如果是自定义的操作，就自己命令），默认是command实现类的类名。 熔断配置就是根据这个名称
                .andCommandKey(HystrixCommandKey.Factory.asKey("smsCommandKey"))
                // 线程池命名，默认是HystrixCommandGroupKey的名称。 线程池配置就是根据这个名称
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("smsThreadPool"))
                // command 熔断相关参数配置
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        // 配置隔离方式：默认采用线程池隔离。还有一种信号量隔离方式,
                        // .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        // 超时时间500毫秒
                        // .withExecutionTimeoutInMilliseconds(500)
                        // 信号量隔离的模式下，最大的请求数。和线程池大小的意义一样
                        // .withExecutionIsolationSemaphoreMaxConcurrentRequests(2)
                        // 熔断时间（熔断开启后，各5秒后进入半开启状态，试探是否恢复正常）
                        // .withCircuitBreakerSleepWindowInMilliseconds(5000)
                )
                // 设置线程池参数
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        // 线程池大小
                        .withCoreSize(1))

        );
    }

    @Override
    protected String run() throws Exception {
        System.out.println("command:"+Thread.currentThread().toString());

        //请求
        URL url = new URL("http://localhost:9002/hystrix/timeout");
        byte[] result = new byte[1024];
        url.openStream().read(result);

        return new String(result);
    }

    @Override
    protected String getFallback()
    {
        System.out.println("command:"+Thread.currentThread().toString());
        return "降级";
    }
}

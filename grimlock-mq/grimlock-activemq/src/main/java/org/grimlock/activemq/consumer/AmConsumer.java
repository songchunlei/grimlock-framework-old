package org.grimlock.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 11:49 2017-12-28
 * @Modified By:
 */
@Component
public class AmConsumer {
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text)
    {
        System.out.println("收到的报文:"+text);
    }

}

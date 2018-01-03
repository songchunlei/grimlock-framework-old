package org.grimlock.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 11:47 2017-12-28
 * @Modified By:
 */
@Service("amproducer")
public class AmProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(Destination destination,final String message)
    {
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}

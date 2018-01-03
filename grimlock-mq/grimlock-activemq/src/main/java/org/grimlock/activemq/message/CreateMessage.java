package org.grimlock.activemq.message;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 11:51 2017-12-28
 * @Modified By:
 */
public class CreateMessage implements MessageCreator{
    @Override
    public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage("测试消息");
    }
}

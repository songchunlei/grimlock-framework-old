package org.grimlock.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.Queue;
import javax.jms.Topic;


/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 11:54 2017-12-28
 * @Modified By:
 */
public class AmConfig {

    public Queue queue(){
        return new ActiveMQQueue("test.queue");
    }

    public Topic topic(){
        return new ActiveMQTopic("test.topic");
    }

}

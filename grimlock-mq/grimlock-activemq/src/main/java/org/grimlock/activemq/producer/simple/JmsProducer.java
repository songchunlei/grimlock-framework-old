package org.grimlock.activemq.producer.simple;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author:chunlei.song@live.com
 * @Description: 最原始的消息队列生产者
 * @Date Create in 14:27 2017-12-27
 * @Modified By:
 */
public class JmsProducer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final int SENDNUM = 10;//发送消息数量

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection = null;//JMS连接
        Session session;//JMS会话
        Destination destination;//JMS队列
        MessageProducer messageProducer;//JMS生产者

        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            /**
             * 参数1： 是否用事务。
             * 参数2： 消息确认。
             *          int AUTO_ACKNOWLEDGE = 1; 自动签收
                        int CLIENT_ACKNOWLEDGE = 2; 客户端自行调用ACKNOWLEDGE手动签收
                        int DUPS_OK_ACKNOWLEDGE = 3; 不是必须签收，消息可能会重复发送
                        int SESSION_TRANSACTED = 0; 将session变成TRANSACTED（疑问？）
             */
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            /**
             * 创建队列
             */
            destination = session.createQueue("HelloWorld");
            /**
             * 消息生产者
             */
            messageProducer = session.createProducer(destination);
            for (int i = 0; i < SENDNUM; i++) {
                String msg = "发送消息"+i+System.currentTimeMillis();
                TextMessage message = session.createTextMessage(msg);
                System.out.println("发送消息"+msg);
                messageProducer.send(message);
            }
            //因为启动了事务,如果其中一个失败会回滚
            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        }
        finally {
            if(connection!=null)
            {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}

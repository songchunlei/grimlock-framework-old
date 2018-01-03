package org.grimlock.rabbitmq.consumer.normal;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 15:04 2017-12-28
 * @Modified By:
 */
public class ConsumerError {

    private final static String EXCHANGE_NAME="direct_log";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.100.27.82");
        /*取缺省
        factory.setUsername();
        factory.setPassword();
        factory.setPort();
        factory.setVirtualHost();
        */
        //定义连接
        Connection connection = factory.newConnection();
        //定义信道
        Channel channel =  connection.createChannel();
        //定义交换器
        //当绑定的是DIREECT，消息会根据Server走路由
        //当绑定的是广播FANOUT，消息会全部广播，和绑定的server无关
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        //声明随机队列
        String queueName = channel.queueDeclare().getQueue();
        String server = "error";
        channel.queueBind(queueName,EXCHANGE_NAME,server);
        System.out.println("waiting message...");


        //类似监听器
        Consumer consumerB = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("Accept:"+envelope.getRoutingKey() +message);
            }
        };

        //队列
        //是否AutoAck
        //
        channel.basicConsume(queueName,true,consumerB);

    }
}

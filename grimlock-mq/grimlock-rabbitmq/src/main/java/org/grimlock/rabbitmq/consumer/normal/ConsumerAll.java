package org.grimlock.rabbitmq.consumer.normal;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 14:51 2017-12-28
 * @Modified By:
 */
public class ConsumerAll {

    private final static String EXCHANGE_NAME="direct_log";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.100.27.82");

        factory.setUsername("scl");
        factory.setPassword("abc@123456");

        //定义连接
        Connection connection = factory.newConnection();
        //定义信道
        Channel channel =  connection.createChannel();
        //定义交换器
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        //声明随机队列
        String queueName = channel.queueDeclare().getQueue();
        String[] serverities = {"error","info","warn"};
        for (String server:
        serverities) {
            //队列和交换器绑定
            channel.queueBind(queueName,EXCHANGE_NAME,server);
        }
        System.out.println("waiting message...");


        //类似监听器
        Consumer consumerA = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("Accept:"+envelope.getRoutingKey() +message);
            }
        };

        //队列
        //是否AutoAck
        //
        channel.basicConsume(queueName,true,consumerA);

    }

}

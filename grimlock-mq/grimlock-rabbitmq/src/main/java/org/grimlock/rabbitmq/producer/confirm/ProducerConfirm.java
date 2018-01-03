package org.grimlock.rabbitmq.producer.confirm;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 17:13 2017-12-28
 * @Modified By:
 */
public class ProducerConfirm {
    //定义日志类型的生产者
    private final static String EXCHANGE_NAME="producer_confirm";
    private final static String ROUTE_KEY ="error";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.100.27.82");

        factory.setUsername("scl");
        factory.setPassword("abc@123456");
        /*
        factory.setPort();
        factory.setVirtualHost();
        */
        //定义连接
        Connection connection = factory.newConnection();
        //定义信道
        Channel channel =  connection.createChannel();
        //将信道设置为发送方确认
        channel.confirmSelect();
        //定义交换器
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);



        for (int i = 0; i < 3; i++) {
            String message = "Hello Word_"+(i+1);
            //rabbitmq是字节传输
            channel.basicPublish(EXCHANGE_NAME,ROUTE_KEY,null,message.getBytes());
            //把生产者堵塞到接收到RABBITMQ收到消息为止
            if(channel.waitForConfirms())
            {
                System.out.println(ROUTE_KEY+":"+message);
            }
            System.out.println("Send "+ROUTE_KEY+": " +message);
        }
        channel.close();
        connection.close();
    }
}

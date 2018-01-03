package org.grimlock.rabbitmq.producer.confirm;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author:chunlei.song@live.com
 * @Description: 异步确认模式
 * @Date Create in 17:19 2017-12-28
 * @Modified By:
 */
public class ProducerConfirmAsync {
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

        //定义交换器
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        //将信道设置为发送方确认
        channel.confirmSelect();

        //增加监听器
        //deliverTag 代表在当前信道里唯一一次投递，
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("handleAck Ack deliverTag ="+l+" mulitple ="+b);
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("handleNack Ack deliverTag ="+l+" mulitple ="+b);
            }
        });


        //返回方法
        //参数为true，如果投递消息无法找到合适队列
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                String msg = new String(bytes);

            }
        });


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

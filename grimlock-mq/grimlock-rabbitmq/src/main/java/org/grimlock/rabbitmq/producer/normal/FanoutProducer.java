package org.grimlock.rabbitmq.producer.normal;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 15:07 2017-12-28
 * @Modified By:
 */
public class FanoutProducer {
    //定义日志类型的生产者
    private final static String EXCHANGE_NAME="fanout_log";

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
        //定义交换器,广播
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        String[] serverities = {"error","info","warn"};

        for (int i = 0; i < 3; i++) {
            String server = serverities[i];
            String message = "Hello Word_"+(i+1);
            //rabbitmq是字节传输
            channel.basicPublish(EXCHANGE_NAME,server,null,message.getBytes());
            System.out.println("Send "+server+": " +message);
        }
        channel.close();
        connection.close();
    }
}

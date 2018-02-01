package org.grimlock.uua.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 14:12 2018-1-4
 * @Modified By:
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"org.grimlock"})
public class UUAServerApp {
    final static Logger logger = LoggerFactory.getLogger(UUAServerApp.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(UUAServerApp.class).web(true)
                .run(args);
        logger.debug(applicationContext.getId() + "已经启动,当前host：{}",
                applicationContext.getEnvironment().getProperty("HOSTNAME"));
    }
}

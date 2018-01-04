package org.grimlock.config.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 14:05 2018-1-4
 * @Modified By:
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServerApp {
    private final static Logger logger = LoggerFactory.getLogger(ConfigServerApp.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(ConfigServerApp.class)
                .web(true).run(args);
        logger.debug(applicationContext.getId() + "已经启动,当前host：{}",
                applicationContext.getEnvironment().getProperty("HOSTNAME"));
    }
}

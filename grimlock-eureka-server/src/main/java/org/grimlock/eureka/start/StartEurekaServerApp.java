package org.grimlock.eureka.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@EnableEurekaServer
@SpringBootApplication
public class StartEurekaServerApp {

    private final static Logger logger = LoggerFactory.getLogger(StartEurekaServerApp.class);

	public static void main(String[] args) {

	    ConfigurableApplicationContext applicationContext =new SpringApplicationBuilder(StartEurekaServerApp.class).run(args);
        logger.debug("eureka已经启动,当前Host:{}",applicationContext.getEnvironment().getProperty("HOSTNAME"));

    }

}

package org.grimlock.sms.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 15:48 2018-1-2
 * @Modified By:
 */

@SpringBootApplication
@EnableEurekaClient
public class SmsServerApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SmsServerApp.class).web(true).run(args);
    }
}

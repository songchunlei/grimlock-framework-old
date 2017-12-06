package org.grimlock.testweb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TestWebApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TestWebApp.class).web(true).run(args);
    }
}

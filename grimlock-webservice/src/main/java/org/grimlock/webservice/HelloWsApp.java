package org.grimlock.webservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * WebService Demo
 * Created by songchunlei on 2018/1/29.
 */
@SpringBootApplication
public class HelloWsApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext =new SpringApplicationBuilder(HelloWsApp.class).run(args);

    }
}

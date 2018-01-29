package org.grimlock.webservice.config;

import org.apache.cxf.Bus;

import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.grimlock.webservice.api.NetBarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * Created by songchunlei on 2018/1/29.
 */
@Configuration
public class CxfConfig {
    @Autowired
    private Bus bus;
    @Autowired
    private NetBarServices netBarServices;

    /**
     * 改变请求位置
     * @return
     */
    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus,netBarServices);
        endpoint.publish("/NetBarServices");
        return endpoint;

    }
}

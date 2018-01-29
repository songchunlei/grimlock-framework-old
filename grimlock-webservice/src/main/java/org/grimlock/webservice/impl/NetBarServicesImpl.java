package org.grimlock.webservice.impl;

import org.grimlock.webservice.api.NetBarServices;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * Created by songchunlei on 2018/1/29.
 */
@WebService(serviceName = "NetBarServices"
            ,targetNamespace = "http://api.webservice.grimlock.org"
            ,endpointInterface = "org.grimlock.webservice.api.NetBarServices")
@Component
public class NetBarServicesImpl implements NetBarServices {
    @Override
    public String sayHello(String username) {
        return "hello"+username;
    }
}

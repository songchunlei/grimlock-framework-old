package org.grimlock.webservice.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by songchunlei on 2018/1/29.
 */
//命名空间，采用接口的包名倒叙
@WebService(targetNamespace = "http://api.webservice.grimlock.org")
public interface NetBarServices {
    @WebMethod
    String sayHello(@WebParam(name = "username") String username);
}


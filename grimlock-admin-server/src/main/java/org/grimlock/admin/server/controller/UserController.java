package org.grimlock.admin.server.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 10:58 2018-1-2
 * @Modified By:
 */
@RestController
public class UserController {

    @Autowired
    EurekaClient eurekaClient;

    @GetMapping("eurka-instance")
    public String serviceUrl()
    {
        InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("GRIMLOCK-ADMIN-SERVER", false);
        return instance.getHomePageUrl();
    }
}

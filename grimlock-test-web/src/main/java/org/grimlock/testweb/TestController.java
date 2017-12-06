package org.grimlock.testweb;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("test")
    public String test(){
        List<InstanceInfo> instanceList = discoveryClient.getInstancesById("sms");
        for(InstanceInfo instanceInfo:instanceList)
        {
            System.out.println("实例："+instanceInfo.getPort());
        }

        InstanceInfo instanceInfo = instanceList.get(0);
        String hostName = instanceInfo.getHostName();
        String hostIp = instanceInfo.getIPAddr();
        int port = instanceInfo.getPort();

        String result = new RestTemplate().getForObject("http://" + hostIp + ":" + port +"/sms/1",String.class);

        return result;
    }

}

package org.grimlock.admin.server.controller;

import org.grimlock.admin.server.Sms;
import org.grimlock.admin.server.command.SmsSendCommand;
import org.grimlock.admin.server.feign.FeignSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 16:11 2018-1-2
 * @Modified By:
 */

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    FeignSmsClient feignSmsClient;

    // get 请求
    @RequestMapping("/test1")
    public String test1() {
        String body = feignSmsClient.querySms();
        return body;
    }

    // post 请求
    @RequestMapping("/test2")
    public String test2(@RequestBody Sms sms) {
        String body = feignSmsClient.sendSms(sms);
        return body;
    }

    @RequestMapping("/test4")
    public Sms test4(String id) {
        Sms sendSmsTemplate = feignSmsClient.getSms(id);
        return sendSmsTemplate;
    }

    @RequestMapping("/test5")
    public String test5() {
        String sendSmsTemplate = feignSmsClient.fail404();
        return sendSmsTemplate;
    }

    @RequestMapping("/test")
    public String test() throws IOException {
        // 普通方式调用接口，不管接口稳不稳定，会一直请求，占用服务器资源，造成雪崩
        URL url = new URL("http://localhost:9002/hystrix/timeout");
        byte[] result = new byte[1024];
        url.openStream().read(result);

        return new String(result);
    }

    @RequestMapping("/hystrix")
    public String test_hystrix() throws IOException{
        // 通过hystrix调用接口，如果接口不稳定，会开启熔断，防止雪崩
        // 同步调用
        System.out.println("http请求##########"+Thread.currentThread().toString());
        return new SmsSendCommand().execute();
    }
}

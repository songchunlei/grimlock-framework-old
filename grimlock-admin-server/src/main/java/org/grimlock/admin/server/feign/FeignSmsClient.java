package org.grimlock.admin.server.feign;

import org.grimlock.admin.server.Sms;
import org.grimlock.admin.server.failback.FeignSmsClientFailBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 16:06 2018-1-2
 * @Modified By:
 */
//有URL参数，表示不走eureka
//@FeignClient(name = "grimlock-sms-server", url="map.baidu.com" fallback = FeignSmsClientFailBack.class)
@FeignClient(name = "grimlock-sms-server", fallback = FeignSmsClientFailBack.class)
public interface FeignSmsClient {
    /** 普通 */
    @RequestMapping(value = "/sms", method = RequestMethod.GET)
    String querySms();

    /** 传递对象 */
    // 实际上是对象转json，作为请求报文
    @RequestMapping(value = "/sms", method = RequestMethod.GET)
    String sendSms(@RequestBody Sms sms);

    /** 获取短信内容 */
    @RequestMapping(value = "/sms/{id}", method = RequestMethod.GET)
    Sms getSms(@PathVariable("id") String id);

    /** 这是一个会报错的请求，测试fallback */
    @RequestMapping(value = "/fail404", method = RequestMethod.GET)
    String fail404();

    @RequestMapping(value = "/hystrix/timeout", method = RequestMethod.GET)
    String timeout();

    @RequestMapping(value = "/hystrix/exception", method = RequestMethod.GET)
    String exception();

}

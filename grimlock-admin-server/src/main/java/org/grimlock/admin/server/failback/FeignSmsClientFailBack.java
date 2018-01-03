package org.grimlock.admin.server.failback;

import org.grimlock.admin.server.Sms;
import org.grimlock.admin.server.feign.FeignSmsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 16:08 2018-1-2
 * @Modified By:
 */
@Component
public class FeignSmsClientFailBack implements FeignSmsClient {

    static final Logger logger = LoggerFactory.getLogger(FeignSmsClientFailBack.class);

    @Override
    public String querySms() {
        logger.warn("调用querySms出错");
        return "querySms出错啦";
    }

    @Override
    public String sendSms(Sms sms) {
        logger.warn("调用sendSms出错");
        return "sendSms出错啦";
    }

    @Override
    public String fail404() {
        logger.warn("调用fail404出错");
        return "fail404出错啦";
    }

    @Override
    public Sms getSms(String id) {
        logger.warn("调用sendSms出错");
        return new Sms("这是错误的","这是错误的");
    }

    @Override
    public String timeout() {
        System.out.println("###调用timeout接口失败，导致降级####" + Thread.currentThread().toString());
        return "调用timeout接口失败，导致降级";
    }

    @Override
    public String exception() {
        System.out.println("###调用有异常的接口，导致降级####" + Thread.currentThread().toString());
        return "调用有异常的接口，导致降级";
    }

    // 降级常用的手段：
    // MOCK： 返回固定的值。比如这个示例使用的就是mock值
    // CACHE： 查询缓存。有些信息在通过接口无法获取时，可以查询本地的缓存。
    // BACKUP： 备用接口。有些场景下，为了防止异常，会做一个备用的接口以备不时只需。
}

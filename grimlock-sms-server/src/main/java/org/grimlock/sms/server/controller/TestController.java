/**
 * 
 */
package org.grimlock.sms.server.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO
 * 测试熔断
 * @author zhang
 * @since v0.0.1 2017年9月8日 上午10:53:12
 */
@Controller
@RequestMapping("/hystrix")
public class TestController {
	
	@ResponseBody
	@RequestMapping("/timeout")
	public String timeOut() throws InterruptedException {
		System.out.println("有人请求timeout啦...，当前时间:" + System.currentTimeMillis());
		// 睡眠3秒，模拟超时
		Thread.sleep(3000L);
		return "ok";
	}
	
	// 随机报错
	@RequestMapping("/exception")
	public String exception(HttpServletResponse servletResponse) throws InterruptedException, IOException {
		System.out.println("有人请求exception接口啦...，当前时间:" + System.currentTimeMillis());
		if(new Random().nextInt(1000) % 2 == 0) {
			// 随机设置响应状态码为503，服务不可用的状态
			servletResponse.setStatus(503);
			servletResponse.getWriter().write("error");
			servletResponse.flushBuffer();
		}
		return "ok";
	}

}

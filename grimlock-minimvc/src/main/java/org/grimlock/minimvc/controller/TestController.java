package org.grimlock.minimvc.controller;

import org.grimlock.minimvc.annotation.Controller;
import org.grimlock.minimvc.annotation.Qualifier;
import org.grimlock.minimvc.annotation.RequestMapping;
import org.grimlock.minimvc.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 16:09 2018-1-4
 * @Modified By:
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Qualifier("testService")
    private TestService testService;

    @RequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response,String param)
    {
        try {
            PrintWriter pw = response.getWriter();
            String result = testService.query();
            pw.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        testService.query();
    }

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, HttpServletResponse response,String param)
    {
        testService.insert();
    }

    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,String param)
    {

    }

}

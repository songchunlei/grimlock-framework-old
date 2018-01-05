package org.grimlock.minimvc.service;

import org.grimlock.minimvc.annotation.Service;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 16:21 2018-1-4
 * @Modified By:
 */
@Service
public class TestServiceImpl implements TestService{
    @Override
    public String query() {
        return "查询成功";
    }

    @Override
    public String insert() {
        return "插入成功";
    }
}

package com.jd.gms.consumer.impl;

import com.jd.gms.consumer.CallRPCService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/18 15:57
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-config.xml")
public class CallRPCServiceImplTest {

    @Autowired
    private CallRPCService callRPCService;

    @Test
    public void printStr() {
        callRPCService.printStr("test call rpc method--");
    }
}
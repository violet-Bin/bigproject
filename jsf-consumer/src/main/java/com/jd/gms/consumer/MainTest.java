package com.jd.gms.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/18 16:34
 * @Description:
 */
public class MainTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-config.xml");
        CallRPCService callRPCService = (CallRPCService) context.getBean("callRPCService");
        callRPCService.printStr("MainTest call rpc method");
    }

}

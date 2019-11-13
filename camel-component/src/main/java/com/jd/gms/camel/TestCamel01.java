package com.jd.gms.camel;

import com.jd.gms.camel.component.FooComponent;
import com.jd.gms.camel.component.SecondComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/11/5 16:09
 * @Description:
 */
public class TestCamel01 {

    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
//        CamelContext camelContext = (CamelContext) context.getBean("camel");
//        SecondComponent secondComponent = (SecondComponent) context.getBean("secondComponent");
//        camelContext.addComponent("sCom", secondComponent);
//        Component fooComponent = camelContext.getComponent("sCom");
//        System.out.println(fooComponent);

        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addComponent("sCom", new FooComponent());
        Component fooComponent = camelContext.getComponent("sCom");
        System.out.println(fooComponent);
    }
}

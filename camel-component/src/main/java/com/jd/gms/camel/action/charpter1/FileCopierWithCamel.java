package com.jd.gms.camel.action.charpter1;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/11/8 15:00
 * @Description: 将一个文件夹下的文件复制到另一个文件夹下
 */
public class FileCopierWithCamel {

    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:E:\\test?noop=true").to("file:E:\\test1");
            }
        });
        camelContext.start();
        Thread.sleep(10000);
        camelContext.stop();
    }

}

package com.jd.gms.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.FileComponent;
import org.apache.camel.component.zookeeper.ZooKeeperComponent;
import org.apache.camel.component.zookeeper.ZooKeeperConfiguration;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/11/5 17:37
 * @Description:
 */
public class TestCamel02 {

    public TestCamel02() {

    }
/*
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        ZooKeeperConfiguration zooKeeperConfiguration = new ZooKeeperConfiguration();
        zooKeeperConfiguration.addZookeeperServer("localhost:2181");
        camelContext.addComponent("zookeeper", new ZooKeeperComponent(zooKeeperConfiguration));

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("zookeeper://localhost:2181/zk_book111").to("mock:result");
            }
        });

        ProducerTemplate template = camelContext.createProducerTemplate();
        //纯Java（不用spring）需要手动start
        camelContext.start();
        for (int i = 0; i < 10; i++) {
            template.sendBody("zookeeper://localhost:2181/zk_book111", "Test Message: " + i);
        }

    }
    */
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addComponent("file", new FileComponent());

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:E:\\test").to("file:E:\\test1");
            }
        });

        ProducerTemplate template = camelContext.createProducerTemplate();
        //纯Java（不用spring）需要手动start
        camelContext.start();
        for (int i = 0; i < 10; i++) {
            template.sendBody("file:E:\\test1", "Test Message: " + i);
        }

    }
}

package com.jd.gms.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.io.File;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/24 22:07
 * @Description: 单个zookeeper, 单元测试
 */
public class TestingServerSample {

    static String path = "/zookeeper";

    public static void main(String[] args) throws Exception {
        TestingServer server = new TestingServer(2181, new File("E:\\var\\temp\\data\\zookeeper1"));
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(server.getConnectString())
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(100, 3))
                .build();

        client.start();
        System.out.println(client.getChildren().forPath(path));
        server.close();
    }

}

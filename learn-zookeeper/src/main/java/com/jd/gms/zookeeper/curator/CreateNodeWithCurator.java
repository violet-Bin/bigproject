package com.jd.gms.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/23 21:20
 * @Description: 使用curator创建节点
 */
public class CreateNodeWithCurator {

    static String path = "/zk-book1/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2182")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(100, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());
//        System.out.println(client.getData());
    }

}

package com.jd.gms.zookeeper.curator.sync;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/23 21:43
 * @Description: 使用curator删除节点
 */
public class DeleteNodeWithCurator {

    static String path = "/zk-book3/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(100, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());
        Stat stat = new Stat();
        byte[] bytes = client.getData().storingStatIn(stat).forPath(path);
        System.out.println(new String(bytes));
        client.delete().deletingChildrenIfNeeded()
                .withVersion(stat.getVersion()).forPath(path);
        System.out.println("ok");


    }

}

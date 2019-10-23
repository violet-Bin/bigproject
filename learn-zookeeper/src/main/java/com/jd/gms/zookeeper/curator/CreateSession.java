package com.jd.gms.zookeeper.curator;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/23 20:48
 * @Description: 使用Curator创建会话
 */
public class CreateSession {

    public static void main(String[] args) throws InterruptedException {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(100, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", 5000, 3000, retryPolicy);
        client.start();
        System.out.println("start........");
        Thread.sleep(Integer.MAX_VALUE);
    }

}

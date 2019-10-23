package com.jd.gms.zookeeper.curator;

import com.sun.javafx.image.IntPixelGetter;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/23 21:06
 * @Description: 使用fluent风格创建zookeeper客户端
 */
public class CreateWithFluent {

    public static void main(String[] args) throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        System.out.println("start............");
        Thread.sleep(Integer.MAX_VALUE);
    }

}

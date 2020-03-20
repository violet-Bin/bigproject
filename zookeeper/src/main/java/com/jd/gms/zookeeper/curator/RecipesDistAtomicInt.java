package com.jd.gms.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/24 17:56
 * @Description: curator 实现分布式计数器
 */
public class RecipesDistAtomicInt {

    static String distAPath = "/curator-recipes-distautomicint-path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(100, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        DistributedAtomicInteger atomicInteger =
                new DistributedAtomicInteger(client, distAPath, new RetryNTimes(3, 1000));
        AtomicValue<Integer> rc = atomicInteger.add(8);//每次加8
        System.out.println("Result: " + rc.succeeded() + " preValue  " +rc.preValue() + "  postValue  " +rc.postValue());
    }
}

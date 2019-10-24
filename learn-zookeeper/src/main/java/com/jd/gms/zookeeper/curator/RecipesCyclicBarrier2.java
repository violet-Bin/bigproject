package com.jd.gms.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/24 18:43
 * @Description:
 */
public class RecipesCyclicBarrier2 {

    static String path = "/curator-recipes_barrier_path1";

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.builder()
                                .connectString("localhost:2181")
                                .sessionTimeoutMs(5000)
                                .retryPolicy(new ExponentialBackoffRetry(100, 3))
                                .build();
                        client.start();
                        DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, path, 5);
                        Thread.sleep(Math.round((Math.random() * 3000)));
                        System.out.println(Thread.currentThread().getName() + "号进入barrier");
                        barrier.enter();
                        System.out.println(Thread.currentThread().getName() + "  启动....");
                        Thread.sleep(Math.round((Math.random() * 3000)));
                        barrier.leave();
                        System.err.println(Thread.currentThread().getName() + " 退出......");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

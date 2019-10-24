package com.jd.gms.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/24 18:07
 * @Description: curator实现分布式Barrier
 */
public class RecipesCyclicBarrier {

    static String path = "/curator-recipes_barrier_path";
    static DistributedBarrier barrier;

    public static void main(String[] args) throws Exception {
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
                        barrier = new DistributedBarrier(client, path);
                        System.out.println(Thread.currentThread().getName() + "号barrier设置");
                        barrier.setBarrier();
                        barrier.waitOnBarrier();
                        System.err.println(Thread.currentThread().getName() + " 启动......");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        Thread.sleep(2000);
        barrier.removeBarrier();
    }


}



//JDK 的 CyclicBarrier
//public class CyclicBarrier {
//
//    public static CyclicBarrier barrier = new CyclicBarrier(5);
//
//    public static void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        executor.submit(new Thread(new Runner("1111")));
//        executor.submit(new Thread(new Runner("2222")));
//        executor.submit(new Thread(new Runner("3333")));
//        executor.submit(new Thread(new Runner("4444")));
//        executor.submit(new Thread(new Runner("5555")));
//        executor.shutdown();
//    }
//
//}
//
//class Runner implements Runnable {
//
//    private String name;
//    public Runner(String name) {
//        this.name = name;
//    }
//
//    @Override public void run() {
//        System.out.println(name + "准备好了.");
//        try {
//            RecipesCyclicBarrier.barrier.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (BrokenBarrierException e) {
//            e.printStackTrace();
//        }
//        System.out.println(name + " run run run  ---");
//    }
//}

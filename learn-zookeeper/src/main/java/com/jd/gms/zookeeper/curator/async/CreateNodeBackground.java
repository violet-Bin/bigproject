package com.jd.gms.zookeeper.curator.async;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/24 15:33
 * @Description: 使用异步接口
 */
public class CreateNodeBackground {

    static String path = "/zk-tt";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(100, 3))
            .build();
    static CountDownLatch semaphore =  new CountDownLatch(2);
    static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        client.start();
        System.out.println("Main thread: " + Thread.currentThread().getName());
        //此处传入自定义的Executor  (将任务交给子线程处理)
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("evevt [code: " + event.getResultCode() + ", type: " + event.getType() + "]");
                        System.out.println("Thread of processResult: " + Thread.currentThread().getName());
                        semaphore.countDown();
                    }
                }, executor)
                .forPath(path, "init".getBytes());
        //此处没有传入自定义的Executor
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("evevt [code: " + event.getResultCode() + ", type: " + event.getType() + "]");
                        System.out.println("Thread of processResult: " + Thread.currentThread().getName());
                        semaphore.countDown();
                    }
                })
                .forPath(path, "init".getBytes());

        semaphore.await();
        executor.shutdown();
    }


}

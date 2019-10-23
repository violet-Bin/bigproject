package com.jd.gms.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/22 16:13
 * @Description: 使用sessionId 和 passwd
 */
public class ZookeeperWithSessionIDAndPasswd implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event: " + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperWithSessionIDAndPasswd());
        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();

        zooKeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperWithSessionIDAndPasswd(), 1L, "test".getBytes());
        zooKeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperWithSessionIDAndPasswd(), sessionId, passwd);
        Thread.sleep(Integer.MAX_VALUE);
    }
}

package com.jd.gms.zookeeper.basics;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/23 9:40
 * @Description: 使用异步async接口获取子节点列表
 */
public class ZookeeperGetChildrenWithAsync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("ReGet Child: " + zooKeeper.getChildren(event.getPath(), true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/zk_fruit3";
        zooKeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperGetChildrenWithAsync());
        connectedSemaphore.await();

        zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create(path + "/b1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.getChildren(path, true, new IChildren2Callback(), null);
        zooKeeper.create(path + "/b2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Thread.sleep(Integer.MAX_VALUE);
    }
}

class IChildren2Callback implements AsyncCallback, AsyncCallback.Children2Callback {

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        System.out.println("Get children znode result: [response code: " + rc
                + ", param path: " + path + ", ctx: " + ctx + ", children list: "
                + children + ", state: " + stat);

    }
}

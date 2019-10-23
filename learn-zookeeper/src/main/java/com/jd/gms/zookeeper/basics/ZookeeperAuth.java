package com.jd.gms.zookeeper.basics;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/23 16:11
 * @Description: Zookeeper权限
 * 没有权限的客户端会话无法访问有权限的数据节点，权限错误也不能访问.
 * 对于删除操作，权限是加在其子节点在，删除当前节点不需要权限.
 */
public class ZookeeperAuth {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        String path = "/zk_book13";
        String path2 = "/zk_book13/c1";
        ZooKeeper zk1 = new ZooKeeper("localhost:2182", 5000, null);
        zk1.addAuthInfo("digest", "foo:true".getBytes());
        zk1.create(path, "".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zk1.create(path2, "".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        ZooKeeper zk2 = new ZooKeeper("localhost:2182", 5000, null);
        try {
            zk2.getData(path, true, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ZooKeeper zk3 = new ZooKeeper("localhost:2182", 5000, null);
        zk3.addAuthInfo("digest", "foo:false".getBytes());
        try {
            zk3.getData(path, true, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.sleep(3000);
        ZooKeeper zk4 = new ZooKeeper("localhost:2182", 5000, null);
        try {
            zk4.delete(path + "/c1", -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

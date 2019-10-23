package com.jd.gms.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/23 18:01
 * @Description: 获取子节点
 */
public class GetChildren {

    public static void main(String[] args) throws InterruptedException {
        String path = "/zk_book16";
        ZkClient zkClient = new ZkClient("localhost:2181", 5000);
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> childrenList) throws Exception {
                System.out.println(parentPath + "'s child changed, childrenList: " + childrenList);
            }
        });

        zkClient.createPersistent(path);
        Thread.sleep(1000);
        System.out.println(zkClient.getChildren(path));
        Thread.sleep(1000);
        zkClient.createPersistent(path + "/c1");
        Thread.sleep(1000);
        zkClient.delete(path + "/c1");
        Thread.sleep(1000);
        zkClient.delete(path);
        Thread.sleep(Integer.MAX_VALUE);


    }
}

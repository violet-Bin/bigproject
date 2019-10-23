package com.jd.gms.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/23 17:13
 * @Description:
 */
public class CreateSession {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("localhost:2181", 5000);
        System.out.println("Zookeeper established");

        String path1 = "/zk-book18";
        String path2 = "/zk-book18/c1";

        zkClient.createPersistent(path2, true);
        zkClient.readData(path1);
        zkClient.deleteRecursive(path1);  //递归删除

    }
}

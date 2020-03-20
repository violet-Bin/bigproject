package com.jd.gms.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/23 18:34
 * @Description:
 */
public class GetData {

    public static void main(String[] args) throws InterruptedException {
        String path = "/zk_book19";
        ZkClient zkClient = new ZkClient("localhost:2182", 5000);
        zkClient.createEphemeral(path, "111");

        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("Node " + dataPath + " changed, new data: " + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("Node " + dataPath + " deleted.");
            }
        });

        System.out.println((String) zkClient.readData(path));
        zkClient.writeData(path, "456");
        Thread.sleep(1000);
        zkClient.delete(path);
        Thread.sleep(Integer.MAX_VALUE);


    }

}

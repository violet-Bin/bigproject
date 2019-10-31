package com.jd.gms.zookeeper.curator;

import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingZooKeeperServer;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/24 22:15
 * @Description: 模拟zookeeper集群测试
 */
public class TestingClusterSample {

    public static void main(String[] args) throws Exception {
        //模拟三台
        TestingCluster cluster = new TestingCluster(3);
        cluster.start();
        System.out.println("----------");
        Thread.sleep(2000);

        TestingZooKeeperServer leader = null;
        for (TestingZooKeeperServer zs : cluster.getServers()) {
            System.out.println(zs.getInstanceSpec().getServerId() + "-");
            System.out.println(zs.getQuorumPeer().getServerState() + "-");
            System.out.println(zs.getInstanceSpec().getDataDirectory().getAbsolutePath());
            if (zs.getQuorumPeer().getServerState().equals("leading")) {
                leader = zs;
            }
        }
        leader.kill();
        System.out.println("--After leader kill: ");
        for (TestingZooKeeperServer zs : cluster.getServers()) {
            System.out.println(zs.getInstanceSpec().getServerId() + "-");
            System.out.println(zs.getQuorumPeer().getServerState() + "-");
            System.out.println(zs.getInstanceSpec().getDataDirectory().getAbsolutePath());
        }
        cluster.stop();
    }
}

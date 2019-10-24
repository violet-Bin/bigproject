package com.jd.gms.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.nio.channels.SelectableChannel;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/24 17:11
 * @Description:
 */
public class RecipesMasterSelect {

    static String masterPath = "/curator-recipes-master-path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(100, 3))
            .build();

    public static void main(String[] args) throws InterruptedException {
        client.start();
        LeaderSelector leaderSelector = new LeaderSelector(client, masterPath, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("成为master角色" + client);
                Thread.sleep(3000);
                System.out.println("完成master操作，释放master权力");
            }
        });
        leaderSelector.autoRequeue();
        leaderSelector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}

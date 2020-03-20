package com.jd.gms.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/24 21:06
 * @Description: EnsurePath采取了静默的节点创建方式，其内部就是试图创建指定节点，如果节点已经存在，就不进行任何操作，
 * 也不抛异常，否则正常创建数据节点。
 */
public class EnsurePathSample {

    static String path = "/zk-aa/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(100, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.usingNamespace("zk-aa");
        EnsurePath ensurePath = new EnsurePath(path);
        ensurePath.ensure(client.getZookeeperClient());
        ensurePath.ensure(client.getZookeeperClient());

        EnsurePath ensurePath2 = client.newNamespaceAwareEnsurePath("/c1");
        ensurePath2.ensure(client.getZookeeperClient());
    }
}

package com.jd.gms.canalclient;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import com.jd.gms.canalclient.processor.CanalProcessor;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/30 9:41
 * @Description:
 */
public class CanalClientNext {

    protected CanalConnector connector;
    protected String destination;

    protected CanalProcessor processor;

    public CanalClientNext(String destination) {
        this(destination, null);
    }

    public CanalClientNext(String destination, CanalConnector connector) {
        this.destination = destination;
        this.connector = connector;
    }

    public void start() {
        System.out.println("CanalClientNext.start------");
        doProcessor();
    }

    private void doProcessor() {
        int batchSize = 1000;
        int emptyCount = 0;
        try {
            connector.connect();
            connector.subscribe();
            connector.rollback();
            while (true) {
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;
                    System.out.println(emptyCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                } else {
                    emptyCount = 0;
                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
                    processor.processEntry(message.getEntries());
                }
                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }
        } finally {
            connector.disconnect();
        }
    }

    public void stop() {
        //配一个dbconfig的开关，此处将其关闭...
    }

    public void setConnector(CanalConnector connector) {
        this.connector = connector;
    }

    public void setProcessor(CanalProcessor processor) {
        this.processor = processor;
    }
}

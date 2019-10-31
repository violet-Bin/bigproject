package com.jd.gms.canalclient;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.jd.gms.canalclient.processor.CanalProcessor;

import javax.annotation.processing.Processor;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/30 9:32
 * @Description:
 */
public class CanalClient {

    private CanalClientNext _canalClient;
    private CanalConnector _connector;
    private String destination;
    private String zkAddress;
    private String username;
    private String password;
    private CanalProcessor processor;

    public void start() {
        _connector = CanalConnectors.newClusterConnector(zkAddress, destination, username, password);
        _canalClient = new CanalClientNext(destination);
        _canalClient.setConnector(_connector);
        _canalClient.setProcessor(processor);
        _canalClient.start();
    }

    public void stop() {
        _canalClient.stop();
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProcessor(CanalProcessor processor) {
        this.processor = processor;
    }
}

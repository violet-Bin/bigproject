package com.jd.gms.canalserver;

import com.alibaba.otter.canal.deployer.CanalController;
import com.alibaba.otter.canal.deployer.CanalLauncher;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/29 15:36
 * @Description:
 */
public class MysqlSlaveProxyServer {

    private static final String CLASSPATH_URL_PREFIX = "classpath:";
    private CanalController controller;
    private List<String> configs;

    public void init() {
        try {
            if (configs == null) {
                configs = new ArrayList<>();
                configs.add(System.getProperty("canal.conf", "classpath:canal.properties"));
            }
            Properties properties = new Properties();
            for (String conf : configs) {
                if (conf.startsWith(CLASSPATH_URL_PREFIX)) {
                    conf = StringUtils.substringAfter(conf, CLASSPATH_URL_PREFIX);
                    properties.load(CanalLauncher.class.getClassLoader().getResourceAsStream(conf));
                } else {
                    InputStream is = null;
                    try {
                        properties.load(is);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (is != null) {
                            is.close();
                        }
                    }
                }
            }
            controller = new CanalController(properties);
            start();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private void start() throws Throwable {
        controller.start();
    }

    public void shutdown() {
        try {
            controller.stop();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void setConfigs(List<String> configs) {
        this.configs = configs;
    }
}

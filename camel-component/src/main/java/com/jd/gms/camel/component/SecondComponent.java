package com.jd.gms.camel.component;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.ComponentConfiguration;
import org.apache.camel.Endpoint;
import org.apache.camel.EndpointConfiguration;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/11/5 16:53
 * @Description:
 */
public class SecondComponent implements Component {



    public SecondComponent() {
        System.out.println("SecondComponent Constructor-----");
    }

    @Override
    public Endpoint createEndpoint(String uri) throws Exception {
        return null;
    }

    @Override
    public boolean useRawUri() {
        return false;
    }

    @Override
    public EndpointConfiguration createConfiguration(String uri) throws Exception {
        return null;
    }

    @Override
    public ComponentConfiguration createComponentConfiguration() {
        return null;
    }

    @Override
    public void setCamelContext(CamelContext camelContext) {

    }

    @Override
    public CamelContext getCamelContext() {
        return null;
    }
}

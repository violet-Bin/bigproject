package com.jd.gms.provider.rpc;

import com.jd.gms.rpc.TService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/18 10:47
 * @Description:
 */
//@Service("providerRPCService")
public class ProviderRPCService implements TService {

//    @Autowired
//    @Qualifier("providerServiceImpl")
    private TService pService;

    @Override
    public void printStr(String str) {
        pService.printStr("ProviderService.printStr: " + str);
    }

    public TService getpService() {
        return pService;
    }

    public void setpService(TService pService) {
        this.pService = pService;
    }
}

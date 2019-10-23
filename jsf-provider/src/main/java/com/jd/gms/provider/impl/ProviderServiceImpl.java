package com.jd.gms.provider.impl;

import com.jd.gms.rpc.TService;
import org.springframework.stereotype.Service;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/18 10:48
 * @Description:
 */
@Service("providerServiceImpl")
public class ProviderServiceImpl implements TService {

    @Override
    public void printStr(String str) {
        System.out.println("ProviderServiceImpl1.printStr: "+str);
    }
}

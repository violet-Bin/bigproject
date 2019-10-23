package com.jd.gms.consumer.impl;

import com.jd.gms.rpc.TService;
import com.jd.gms.consumer.CallRPCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/10/18 15:54
 * @Description: 服务匹配是依靠 interface（接口） 以及 alias（别名）两个配置来完成的；
 */
@Service("callRPCService")
public class CallRPCServiceImpl implements CallRPCService {

    @Autowired
    @Qualifier("cService")
    private TService cService;

    @Override
    public void printStr(String str) {
        cService.printStr(str);
    }

}

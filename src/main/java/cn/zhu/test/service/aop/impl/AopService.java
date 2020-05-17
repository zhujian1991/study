package cn.zhu.test.service.aop.impl;

import cn.zhu.test.config.PermissionVerification;
import cn.zhu.test.dao.AspectDao;
import cn.zhu.test.service.aop.IAopService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AopService implements IAopService {
    Logger log = LoggerFactory.getLogger(AopService.class);

    @Autowired
    private AspectDao dao;

    @Override
    public void login() {
        String name = dao.getName();
        log.info("登陆中。。。。");
        System.out.println(name);
    }

    @Override
    public void download() {
        System.out.println("下载中、、、、");
    }
}

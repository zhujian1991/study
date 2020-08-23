package cn.zhu.test.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * description aop切面增强
 * @className PermissionVerification
 * @author 程序老仁
 * @date 2020/5/17
 */   

@Aspect
@Configuration
public class PermissionVerification {
    Logger log = LoggerFactory.getLogger(PermissionVerification.class);
    /**
     * 权限校验
     */
    @Before(value = "cn.zhu.test.config.aop.CommonJoinPointConfig.iAopServiceLogin()")
    public void canLogin(JoinPoint joinPoint) {
        log.info("一个---我正在校验啦！！！！----joinPoint 切点：{}", joinPoint);

    }
    /**
     * 权限校验
     */
    @Before(value = "cn.zhu.test.config.aop.CommonJoinPointConfig.iAopService()")
    public void canLogin1(JoinPoint joinPoint) {
        log.info("二个---我正在校验啦！！！！----joinPoint 切点：{}", joinPoint);
    }
    /**
     * 权限校验
     */
    @Before(value = "cn.zhu.test.config.aop.CommonJoinPointConfig.AopService()")
    public void canLogin2(JoinPoint joinPoint) {
        log.info("三个---我正在校验啦！！！！----joinPoint 切点：{}", joinPoint);
    }
    /**
     * 校验之后做一些处理（无论是否成功都做处理）
     */
    @After("cn.zhu.test.config.aop.CommonJoinPointConfig.AopService()")
    public void saveMessage() {
        //做一些后置处理
        log.info("我正在处理啦！！！！");
    }

}


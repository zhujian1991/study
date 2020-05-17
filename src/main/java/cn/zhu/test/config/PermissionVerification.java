package cn.zhu.test.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * description aop切面
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
    @Before(value = "execution(* cn.zhu.test.dao.*.*(..))")
    public void canLogin(JoinPoint joinPoint) {
        //做一些登陆校验
        log.info("我正在校验啦！！！！----joinPoint 切点：{}", joinPoint);

    }

    /**
     * 校验之后做一些处理（无论是否成功都做处理）
     */
    @After("execution(* cn.zhu.test.dao.*.*(..))")
    public void saveMessage() {

        //做一些后置处理
        log.info("我正在处理啦！！！！");
    }

}


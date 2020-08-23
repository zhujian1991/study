package cn.zhu.test.config.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * description 切面点集中配置
 * @className CommonJoinPointConfig
 * @author 程序老仁
 * @date 2020/5/17
 */   

public class CommonJoinPointConfig {
    /**
     * description 单个校验都能成功， 第一个aop到具体的方法，第二个aop到接口，第三个aop到具体的类。
     * @author 程序老仁
     * @date 2020/5/17
     */
    @Pointcut(value = "execution(* cn.zhu.test.service.aop.IAopService.login(..))")
    public void iAopServiceLogin() {}
    @Pointcut("execution(* cn.zhu.test.service.aop.IAopService.*(..))")
    public void iAopService() {}
    @Pointcut("execution(* cn.zhu.test.service.aop.impl.AopService.*(..))")
    public void AopService() {}
}

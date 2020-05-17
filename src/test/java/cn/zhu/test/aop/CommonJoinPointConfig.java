package cn.zhu.test.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {
    @Pointcut("execution(* cn.zhu.test.aop.*.*(..))")
    public void serviceLayerExecution() {
        System.out.println("config 方法。。。。。");
    }
}

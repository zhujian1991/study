package cn.zhu.test.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect //声明自己是一个切面类
public class MyAspect {
    /**
     * 前置通知
     */
    //@Before是增强中的方位
    // @Before括号中的就是切入点了
    //before()就是传说的增强(建言):说白了，就是要干啥事.
//    @Before("execution(* cn.zhu.test..*(..))")
    @Before("CommonJoinPointConfig.serviceLayerExecution()")
    public void before(){
        System.out.println("前置通知....");
    }
}

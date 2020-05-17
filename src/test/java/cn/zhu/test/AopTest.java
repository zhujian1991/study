package cn.zhu.test;

import cn.zhu.test.aop.CommonJoinPointConfig;
import cn.zhu.test.aop.Dog;
import cn.zhu.test.service.aop.IAopService;
import org.apache.commons.lang3.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.security.auth.Subject;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.regex.Pattern;


//// 运行器，代表在什么环境下运行，@RunWith(JUnit4.class)☞JUnit4来运行
@RunWith(SpringRunner.class)
//// springBoot专用于单元测试的注解。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AopTest {
    @Autowired
    private IAopService aopService;
    /**
     * description 配置代理
     * @param 
     * @author 程序老仁
     * @date 2020/5/16
     * @return 
     */   
    @org.junit.Test
    public void getHello() throws Exception{
        ApplicationContext ctx = new ClassPathXmlApplicationContext("SpringAOP.xml");
        IAopService subject1 = (IAopService)ctx.getBean("SubjectImpl1");
        IAopService subject2 = (IAopService)ctx.getBean("SubjectImpl2");
        subject1.login();
    }

    /**
     * description 配置代理
     * @param
     * @author 程序老仁
     * @date 2020/5/16
     * @return
     */
    @org.junit.Test
    public void getHello1() throws Exception{

        aopService.login();
        System.out.println("---------------");
        aopService.download();
    }

}

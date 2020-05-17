package cn.zhu.test.test;

import cn.zhu.test.service.aop.IAopService;
import org.springframework.beans.factory.annotation.Autowired;


// springBoot专用于单元测试的注解。

//// 运行器，代表在什么环境下运行，@RunWith(JUnit4.class)☞JUnit4来运行
//@RunWith(SpringRunner.class)
//// springBoot专用于单元测试的注解。
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
    public void getHello1() throws Exception{
        aopService.login();
        System.out.println("---------------");
        aopService.download();
    }

}

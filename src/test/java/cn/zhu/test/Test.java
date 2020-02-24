package cn.zhu.test;

import cn.zhu.test.bean.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {
    @Autowired
    private TestRestTemplate template;
    /*
        从配置读取数据
        @Autowired 顾名思义，就是自动装配。其作用是替代Java代码里面的getter/setter与bean属性中的property。
        @Autowired 的原理是什么？
        　　其实在启动spring IoC时，容器自动装载了一个AutowiredAnnotationBeanPostProcessor后置处理器，
        当容器扫描到@Autowied、@Resource或@Inject时，就会在IoC容器自动查找需要的bean，并装配给该对象的属性
     */
     @Autowired
    private User userDemo;

    @org.junit.Test
    public void getHello() throws Exception{
        System.out.println("程序老仁的getHello入口 +++++++++++++  ");

        User user = new User();
        String name = user.getName();
        int age = user.getAge();
        System.out.println("程序老仁的姓名："+name);
        // int自动初始化为0
        System.out.println("程序老仁的年龄："+age);

        System.out.println("程序老仁配置的姓名："+userDemo.getName());
        System.out.println("程序老仁配置的年龄："+userDemo.getAge());
    }
}

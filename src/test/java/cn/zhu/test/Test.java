package cn.zhu.test;

import cn.zhu.test.bean.User;
import cn.zhu.test.dao.StudentDao;
import cn.zhu.test.entity.StudentEntity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

// 运行器，代表在什么环境下运行，@RunWith(JUnit4.class)☞JUnit4来运行
@RunWith(SpringRunner.class)
// springBoot专用于单元测试的注解。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {
    @Autowired
    private TestRestTemplate template;

    @Autowired
    private StudentDao dao;
    /*  从配置读取数据
        @Autowired 顾名思义，就是自动装配。其作用是替代Java代码里面的getter/setter与bean属性中的property。
        @Autowired 的原理是什么？
        　　其实在启动spring IoC时，容器自动装载了一个AutowiredAnnotationBeanPostProcessor后置处理器，
        当容器扫描到@Autowied、@Resource或@Inject时，就会在IoC容器自动查找需要的bean，并装配给该对象的属性
     */
     @Autowired
    private User userDemo;

    @org.junit.Test
    public void getHello() throws Exception{
        String  aaa = "15.00";
        BigDecimal bigDecimal = new BigDecimal(aaa);

        System.out.println(bigDecimal);
        StudentEntity user = new StudentEntity();

        for (int i = 0; i < 100000 ; i++) {
            //user.setId(Long.valueOf(i));
            user.setName("狗子1"+i);
            user.setHight(i);
            dao.save(user);
        }
//        System.out.println("程序老仁的getHello入口 +++++++++++++  ");
//        System.out.println("程序老仁配置的姓名："+userDemo.getName());
//        System.out.println("程序老仁配置的年龄："+userDemo.getAge());
//
//        Boolean aaa=  true;
//
//        while (aaa){
//            System.out.println("你进来了吗");
//            aaa = false;
//        }
    }
}

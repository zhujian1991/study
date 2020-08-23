package cn.zhu.test.fastJson;

import cn.zhu.test.entity.UserEntity;
import com.alibaba.fastjson.JSON;


// 运行器，代表在什么环境下运行，@RunWith(JUnit4.class)☞JUnit4来运行
//@RunWith(SpringRunner.class)
// springBoot专用于单元测试的注解。
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FastJsonTest {

    /**
     * description 测试fastJson
     * @author 程序老仁
     * @date 2020/5/16
     */
    @org.junit.Test
    public void getHello() throws Exception{
        UserEntity UserEntity = new UserEntity();
        UserEntity.setId(10);
        UserEntity.setName("狗娃子");
        String text = JSON.toJSONString(UserEntity); //序列化
        System.out.println(text);
        String demo ="{\"id\":50,\"name\":\"娃子\"}";
        UserEntity demoUserEntity = JSON.parseObject(demo,UserEntity.class);
        System.out.println(demoUserEntity.getName());
        System.out.println(demoUserEntity.getId());
        System.out.println("-----------分割线-----------");

        String demo1 ="{\"id\":50,\"name\":\"娃子\"}";
        UserEntity demoUserEntity1 = JSON.parseObject(demo1,UserEntity.class);
        System.out.println(demoUserEntity1.getName());
        System.out.println(demoUserEntity1.getId());
    }


}

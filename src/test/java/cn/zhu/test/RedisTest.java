package cn.zhu.test;

import cn.zhu.test.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private RedisDao redisDao;

    @Test
    public void setRedisDao() {
        //redisDao.setKey("zhu","jian");
        String zhu = redisDao.getValue("zhu");

        System.out.println(zhu);
    }
}

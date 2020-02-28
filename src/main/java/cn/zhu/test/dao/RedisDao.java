package cn.zhu.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setKey(String key,String value){
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(key,"zhuyong");
//        ListOperations<String, String> stringStringListOperations = redisTemplate.opsForList();
        System.out.println("已经执行完成设置值");
//        stringStringListOperations.set(key,1,value);
    }

    public String getValue (String demo){
        ValueOperations<String, String> string1 = redisTemplate.opsForValue();
        return string1.get(demo);
//        ListOperations<String, String> stringStringListOperations = redisTemplate.opsForList();
//        String a = stringStringListOperations.index(demo, 1);
//        return a;
    }
}

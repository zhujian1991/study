package cn.zhu.test.controller;

import cn.zhu.test.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * redies测试
 * @程序老仁
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisDao redisDao;

    /**
     * PathVariable 标签是占位符（这里不能乱用，否则会报错）
     * @param demo
     */
    @GetMapping("/set/{demo}")
    public void setString(@PathVariable(value = "demo", required = false) String demo){
        redisDao.setKey(demo,"zhujian");
        System.out.println("已经执行完成设置值");
    }

    @GetMapping(value = "/get/{demo}")
    @ResponseBody
    public String getString (@PathVariable(value = "demo", required = false) String demo){
        System.out.println(redisDao.getValue(demo));
        return redisDao.getValue(demo);
    }

}

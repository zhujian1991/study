package cn.zhu.test.SuanfaTest;

import cn.zhu.test.service.aop.IAopService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;

public class SuanTest {
    /**
     * description 算法
     * @param 
     * @author 程序老仁
     * @date 2020/5/16
     * @return 
     */   
    @org.junit.Test
    public void getHello() throws Exception{

    }

    private int getMax(){
        int [] xxx ={4,3,21,6,4,3,2,0,5};
        HashMap<Integer,Integer> head = new HashMap<>();
        HashMap<Integer,Integer> tail = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();

        for (int num:xxx
             ) {
            if(!set.contains(num)){
                set.add(num);
                head.put(num,1);
                tail.put(num,1);
                if(tail.containsKey(num-1)){
                    int preLen = tail.get(num-1);
                    int pre = num-preLen;
                    int all = preLen+1;
                }
            }

        }
        return 0;
    }

}

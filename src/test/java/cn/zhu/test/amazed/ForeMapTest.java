package cn.zhu.test.amazed;

import java.time.DayOfWeek;
import java.util.*;

/**
 * @className EnumMapTest
 * @description TODO map遍历最佳实践
 * @author 程序老仁
 * @date 2020/3/28
 */   

public class ForeMapTest {
    @org.junit.Test
    public void getTest() throws Exception{
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("111", "222");
        hm.put("112", "333");
        System.out.println("---------遍历下面的values--------");
        Collection<String> values = hm.values();
        for (String s:values ) {
            System.out.println(s);
        }
        System.out.println("---------迭代器遍历下面的values--------");
        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
        System.out.println("---------用最简单的遍历下面的 key --------");
        Set<String> strings = hm.keySet();
        for (String ii: strings) {
            System.out.println(ii);
        }
        System.out.println("----------- 遍历 key - value --------");
        Set<Map.Entry<String, String>> entrySet = hm.entrySet();
        Iterator<Map.Entry<String, String>> iter = entrySet.iterator();
        while(iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }


}




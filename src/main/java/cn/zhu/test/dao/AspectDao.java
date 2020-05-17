package cn.zhu.test.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AspectDao {
    public String getName(){
        return "dao的name";
    }
}

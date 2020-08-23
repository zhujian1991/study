package cn.zhu.test.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AspectDao {
    Logger log = LoggerFactory.getLogger(AspectDao.class);
    public String getName(){
        log.info("dao层数据在清洗中。。。。");
        return "dao的name";
    }
}

package cn.zhu.test.service.common;

import cn.zhu.test.entity.AnimalEntity;
import cn.zhu.test.entity.StudentEntity;

import java.util.concurrent.ExecutionException;

/**
 * jpa的service类
 * @程序老仁
 */
public interface JpaService {
    /**
     * 获取animal对象
     */
   public AnimalEntity getAnimalById(Integer id) ;

   public StudentEntity getStudentById(Long id);

}

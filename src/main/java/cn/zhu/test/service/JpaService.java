package cn.zhu.test.service;

import cn.zhu.test.entity.Animal;
import cn.zhu.test.entity.StudentEntity;

/**
 * jpa的service类
 * @程序老仁
 */
public interface JpaService {
    /**
     * 获取animal对象
     */
   public Animal getAnimalById(Integer id);

   public StudentEntity getStudentById(Long id);

}

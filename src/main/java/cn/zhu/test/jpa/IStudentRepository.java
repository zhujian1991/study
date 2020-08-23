package cn.zhu.test.jpa;


import cn.zhu.test.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * 
 * </p>
 * 
 * @version 1.0
 * @author 程序老仁
 * @since 2020-05-31
 */
public interface IStudentRepository extends JpaRepository<StudentEntity, Long>, JpaSpecificationExecutor<StudentEntity> {

}

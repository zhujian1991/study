package cn.zhu.test.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.zhu.test.entity.AnimalEntity;

/**
 * <p>
 * 
 * </p>
 * 
 * @version 1.0
 * @author 程序老仁
 * @since 2020-05-31
 */
public interface IAnimalRepository extends JpaRepository<AnimalEntity, Integer>, JpaSpecificationExecutor<AnimalEntity> {

}

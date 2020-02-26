package cn.zhu.test.dao;

import cn.zhu.test.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalDao extends JpaRepository<Animal, Integer> {
}

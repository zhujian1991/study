package cn.zhu.test.dao;

import cn.zhu.test.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<StudentEntity,Long> {
}

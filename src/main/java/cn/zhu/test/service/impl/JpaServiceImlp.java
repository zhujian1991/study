package cn.zhu.test.service.impl;

import cn.zhu.test.dao.AnimalDao;
import cn.zhu.test.dao.StudentDao;
import cn.zhu.test.entity.Animal;
import cn.zhu.test.entity.StudentEntity;
import cn.zhu.test.service.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaServiceImlp implements JpaService {

    @Autowired
    AnimalDao animalDao;

    @Autowired
    StudentDao studentDao;

    @Override
    public Animal getAnimalById(Integer id) {
        return animalDao.getOne(id);
    }

    @Override
    public StudentEntity getStudentById(Long id) {
        return studentDao.getOne(id);
    }
}

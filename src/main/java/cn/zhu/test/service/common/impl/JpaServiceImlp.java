package cn.zhu.test.service.common.impl;

import cn.zhu.test.entity.AnimalEntity;
import cn.zhu.test.entity.StudentEntity;
import cn.zhu.test.jpa.IAnimalRepository;
import cn.zhu.test.jpa.IStudentRepository;
import cn.zhu.test.service.common.JpaService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class JpaServiceImlp implements JpaService {

    @Autowired
    IAnimalRepository animalRepository;

    @Autowired
    IStudentRepository studentRepository;

    @SneakyThrows
    @Override
    public AnimalEntity getAnimalById(Integer id) {


        return animalRepository.getOne(id);
    }

    @Override
    public StudentEntity getStudentById(Long id) {
        return studentRepository.getOne(id);
    }
}

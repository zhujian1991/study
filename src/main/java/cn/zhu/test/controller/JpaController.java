package cn.zhu.test.controller;
import cn.zhu.test.dao.AnimalDao;
import cn.zhu.test.entity.Animal;
import cn.zhu.test.entity.StudentEntity;
import cn.zhu.test.service.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Jpa的测试类
 * @author 程序老仁
 */
@RestController
@RequestMapping("/animal")
public class JpaController {

    @Autowired
    private JpaService jpaService;
    /**
     * 测试专用，获取animal对象
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Animal getAnimal(@PathVariable("id") int id){
        return  jpaService.getAnimalById(id);
    }

    @GetMapping(value = "/student/{id}")
    public StudentEntity getOne(@RequestParam(value = "id", required = false) Long id) {
        return jpaService.getStudentById(id);
    }
}

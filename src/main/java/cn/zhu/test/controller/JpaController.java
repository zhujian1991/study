package cn.zhu.test.controller;
import cn.zhu.test.dao.AnimalDao;
import cn.zhu.test.entity.Animal;
import cn.zhu.test.entity.StudentEntity;
import cn.zhu.test.service.JpaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Jpa的测试类
 * @author 程序老仁
 */
@RestController
@RequestMapping("/animal")
@Api
public class JpaController {

    @Autowired
    private JpaService jpaService;
    /**
     * 测试专用，获取animal对象
     */
    @ApiOperation(value = "获得动物对象",notes = "得到动物表中的json对象")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Animal getAnimal(@PathVariable("id") int id){
        return  jpaService.getAnimalById(id);
    }

    @GetMapping(value = "/student/{id}")
    public StudentEntity getOne(@RequestParam(value = "id", required = false) Long id) {
        return jpaService.getStudentById(id);
    }
}

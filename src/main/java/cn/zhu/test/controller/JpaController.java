package cn.zhu.test.controller;
import cn.zhu.test.entity.AnimalEntity;
import cn.zhu.test.entity.StudentEntity;
import cn.zhu.test.service.common.JpaService;
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
    public AnimalEntity getAnimal(@PathVariable("id") int id){
        return  jpaService.getAnimalById(id);
    }

    @GetMapping(value = "/student/{id}")
    @ApiOperation(value = "获取学生对象",notes = "")
    public StudentEntity getOne(@RequestParam(value = "id", required = false) Long id) {
        return jpaService.getStudentById(id);
    }
}

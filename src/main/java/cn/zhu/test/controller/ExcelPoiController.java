package cn.zhu.test.controller;

import cn.zhu.test.dto.ReqStudentDto;
import cn.zhu.test.entity.StudentEntity;
import cn.zhu.test.service.excel.IStudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelPoiController {

    @Autowired
    private IStudentService studentService;

    @PostMapping("/student")
    @ApiOperation(value = "导出",notes = "excel导出测试")
    public String excelStudents(@RequestBody ReqStudentDto dto) {
        try {
            studentService.excelStudents(dto);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("导出错误+------------");
        }
        return "hello，程序老仁，这是excel的返回";
    }

    @ApiOperation(value = "查询学生对象",notes = "测试")
    @PostMapping("/query")
    public Response query(@RequestBody ReqStudentDto dto) {
        Response response = studentService.queryStudents(dto);
        return response ;
    }
    @ApiOperation(value = "查询学生对象",notes = "测试")
    @GetMapping("/all")
    public List<StudentEntity>  all() {
        List<StudentEntity > list = studentService.all();
        return list ;
    }
    @ApiOperation(value = "查询学生对象",notes = "测试")
    @GetMapping("/allW")
    public List<StudentEntity>  allW() {
        List<StudentEntity > list = studentService.allW();
        return list ;
    }
}

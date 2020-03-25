package cn.zhu.test.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelPoiController {

    @RequestMapping("/excel")
    @ApiOperation(value = "程序老仁",notes = "excel")
    public String index() {

        return "hello，程序老仁";
    }

}

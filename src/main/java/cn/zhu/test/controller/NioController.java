package cn.zhu.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("这是第一个swagger2")
public class NioController {
    @RequestMapping("/nin")
    @ResponseBody
    @ApiOperation(value = "程序老仁",notes = "swagger")
    public String index(Model model) {
        return "hello，程序老仁";
    }

}

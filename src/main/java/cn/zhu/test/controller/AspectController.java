package cn.zhu.test.controller;

import cn.zhu.test.service.aop.IAopService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/aspect")
@RestController
public class AspectController {

    @Autowired
    private IAopService service;

    @ApiOperation(value = "动态代理测试",notes = "测试")
    @GetMapping("/get")
    public String get(Model model) {
        service.login();
        return "hello，程序老仁，这是aspect的调用返回";
    }
}

package cn.zhu.test.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hell1o")
    @ResponseBody
    public String index(Model model) {
       // model.addAttribute("hello", "你好");
        return "hello，程序老仁";
    }

}

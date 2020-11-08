package cn.zhu.test.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/thread")
public class ThreadController {

    @RequestMapping("/get")
    public String test2(@RequestParam(value = "name") String name) {


        return null;
    }

}

package cn.zhu.test.controller;

import cn.zhu.test.domain.User;
import cn.zhu.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/account")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/{id}")
    @ResponseBody
    public User get(@PathVariable Long id) {
        return userService.get(id);
    }


    @RequestMapping("/userx/{id}&{name}")
    public ModelAndView add(@PathVariable Long id,@PathVariable String name){
//        userService.add(id, name);
        User user1 = new User();
        user1.setId(id);
        user1.setName(name);
        userService.insert(user1);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name1","ok");
        return modelAndView;
    }
    @RequestMapping("/user3/{id}")
    public ModelAndView test1(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "大家好啊");
        System.out.println("ModelAndView:hello");
        User user = userService.get(id);
        String name = user.getName();
        System.out.println("ModelAndView:name="+name);
        modelAndView.addObject("name1",name);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping("/user4/{name}")
    public ModelAndView test2(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "大家好啊");
        System.out.println("ModelAndView:test2");
        List list = userService.likeName(name);
        System.out.println("ModelAndView:list="+list);
        User o1 = (User)list.get(1);
        modelAndView.addObject("userId1",o1.getId());
        modelAndView.addObject("userName1",o1.getName());

        User o2 = (User)list.get(2);
        modelAndView.addObject("userId2",o2.getId());
        modelAndView.addObject("userName2",o2.getName());

        Object o = list.get(2);
        System.out.println("测试："+o);

        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/user5")
    public ModelAndView test7() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "大家好啊");
        System.out.println("ModelAndView:hello");
        HashMap user = userService.allUser();
        String name = user.toString();
        System.out.println("ModelAndView:hashmap="+name);
        modelAndView.addObject("hashmap",name);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}

package cn.zhu.test.controller;

import cn.zhu.test.entity.Foods;
import cn.zhu.test.entity.Pizza;
import cn.zhu.test.enumDemo.FoodsCtrategy;
import cn.zhu.test.enumDemo.SelectFoodsEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.Assert.assertTrue;

/**
 * description 枚举测试，策略模式
 * @className MyEnumController
 * @author 程序老仁
 * @date 2020/5/17
 */   

@RestController
@RequestMapping("/enum")
public class MyEnumController {

    @ApiOperation(value = "enum测试",notes = "测试")
    @GetMapping("/get")
    public String get(Model model) {
        Pizza pz = new Pizza();
        pz.setStatus(Pizza.PizzaStatus.READY);
        pz.deliver();
        assertTrue(pz.getStatus() == Pizza.PizzaStatus.DELIVERED);
        return "hello，程序老仁，这是enum的调用返回";
    }

    @ApiOperation(value = "enum策略",notes = "测试")
    @GetMapping("/strategy")
    public String strategy(Model model) {
        Foods foods = new Foods();
        foods.setName("花生");
        foods.setPrice("8块");
        FoodsCtrategy.NORMAL.getFoods(foods);
        SelectFoodsEnum.getInstance().getFoods(foods);
        return "hello，程序老仁，这是enum的调用返回";
    }

}

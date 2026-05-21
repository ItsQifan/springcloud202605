package com.zhouchuanxiang.order.controller;


import com.zhouchuanxiang.order.feign.StockFeignService;
import com.zhouchuanxiang.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouchuanxiang
 * @date 2026-05-20
 * @description 订单控制器
 */
//自动刷新
@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController {

    //nacos内如果格式是YAML，不要忘记值前面的 空格！
    @Value("${testValue:default}")
    private String testValue;

    @Autowired
    private OrderService orderService;


    /**
     * 测试订单接口
     * @return
     */
    @RequestMapping("/create")
    public String createOrder () {
        System.out.println("testValue: " + testValue);
        orderService.createOrder();


        return "order create success";
    }
}

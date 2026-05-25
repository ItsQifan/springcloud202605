package com.zhouchuanxiang.order.controller;

import com.zhouchuanxiang.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过 Feign 调用 stock，演示 Sentinel 对远程调用的保护（需在配置中开启 feign.sentinel.enabled）
 */
@RestController
@RequestMapping("/order/demo")
public class SentinelDemoController {

    @Autowired
    private StockFeignService stockFeignService;

    @GetMapping("/feign-flow")
    public String feignFlow() {
        return stockFeignService.demoFlow();
    }
}

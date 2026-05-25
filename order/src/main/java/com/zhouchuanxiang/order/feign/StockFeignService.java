package com.zhouchuanxiang.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhouchuanxiang
 * @date 2024/5/19
 * @description 声明要调用的服务接口
 */
@FeignClient(name = "stock", path = "/stock", fallback = StockFeignFallback.class)
public interface StockFeignService {

    //声明要调用的rest接口对应的方法
    @RequestMapping("/reduce")
    String reduceStock(@RequestParam Integer productId);

    /** 调用 stock 的 Sentinel 流控演示接口，便于在 order 侧观察 Feign 熔断 */
    @RequestMapping("/demo/flow")
    String demoFlow();
}

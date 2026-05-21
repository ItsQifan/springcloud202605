package com.zhouchuanxiang.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhouchuanxiang
 * @date 2024/5/19
 * @description 声明要调用的服务接口
 */
@FeignClient(name = "stock",path = "/stock")
public interface StockFeignService {

    //声明要调用的rest接口对应的方法
    @RequestMapping("/reduce")
    public String reduceStock(@RequestParam Integer productId);
}

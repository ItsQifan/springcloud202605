package com.zhouchuanxiang.order.feign;

import org.springframework.stereotype.Component;

/**
 * Feign + Sentinel 熔断降级时的兜底实现（经典用法之三）
 */
@Component
public class StockFeignFallback implements StockFeignService {

    @Override
    public String reduceStock(Integer productId) {
        return "fallback: stock reduce blocked or unavailable, productId=" + productId;
    }

    @Override
    public String demoFlow() {
        return "fallback: stock demo/flow unavailable";
    }
}

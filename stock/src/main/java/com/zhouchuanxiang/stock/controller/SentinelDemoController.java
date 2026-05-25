package com.zhouchuanxiang.stock.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
//核心概念
//资源（resource）：被保护的名字，如 stockDemoFlow（@SentinelResource 的 value）。
//流控（Flow）：QPS/并发线程数超限 → 走 blockHandler。
//熔断（Degrade）：慢调用、异常比例等 → 走 fallback 或 block。
//热点参数：对某个参数值（如 productId=9）单独限流。
/**
 * Sentinel 经典用法示例（配合 Dashboard 127.0.0.1:8858 配置规则后测试）
 */
@RestController
@RequestMapping("/stock/demo")
public class SentinelDemoController {

    /**
     * 流控示例：在 Dashboard 为资源名 stockDemoFlow 配置 QPS 阈值（如 2），
     * 然后用 ab / JMeter 或浏览器快速刷新本接口观察限流。
     */
    @GetMapping("/flow")
    //sentinel经典用法之一 资源定义，使用注解定义资源 SentinelResource
    @SentinelResource(value = "stockDemoFlow", blockHandler = "flowBlockHandler")
    public Map<String, Object> demoFlow() {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "ok");
        result.put("time", System.currentTimeMillis());
        return result;
    }

    /** 限流时的回调，方法签名需与原方法一致并多一个 BlockException 参数 */
    public Map<String, Object> flowBlockHandler(BlockException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "blocked by sentinel (flow)");
        result.put("rule", ex.getRule() != null ? ex.getRule().toString() : null);
        return result;
    }

    /**
     * 熔断降级示例：在 Dashboard 为 stockDemoDegrade 配置慢调用比例或异常比例规则，
     * 访问时加大 sleepMs（如 500）模拟慢调用触发降级。
     */
    @GetMapping("/degrade")
    @SentinelResource(value = "stockDemoDegrade", fallback = "degradeFallback")
    public String demoDegrade(@RequestParam(defaultValue = "50") long sleepMs) throws InterruptedException {
        Thread.sleep(sleepMs);
        return "ok after " + sleepMs + "ms";
    }

    /** 业务异常或触发熔断时的 fallback */
    public String degradeFallback(long sleepMs, Throwable t) {
        return "degraded: " + t.getClass().getSimpleName() + ", sleepMs=" + sleepMs;
    }

    /**
     * 热点参数限流：Dashboard 中资源 stockDemoHotParam 配置热点规则，对 productId 单独限流。
     */
    @GetMapping("/hot")
    @SentinelResource(value = "stockDemoHotParam", blockHandler = "hotBlockHandler")
    public String demoHotParam(@RequestParam Integer productId) {
        return "hot param ok, productId=" + productId;
    }

    public String hotBlockHandler(Integer productId, BlockException ex) {
        return "hot param blocked, productId=" + productId;
    }
}

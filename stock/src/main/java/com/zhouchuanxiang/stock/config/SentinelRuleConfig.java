package com.zhouchuanxiang.stock.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * 经典用法之二：启动时用代码加载流控规则（与 Dashboard 配置二选一或并存，后者会推送覆盖需留意）
 * 本示例对 stockDemoFlow 限制 QPS=2，便于不配置 Dashboard 也能快速看到限流效果。
 */
//@Configuration
public class SentinelRuleConfig {

//    @PostConstruct
    public void initFlowRules() {
        FlowRule rule = new FlowRule();
        rule.setResource("stockDemoFlow");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(2);
        FlowRuleManager.loadRules(Collections.singletonList(rule));
    }
}

package com.zhouchuanxiang.order.service;

import com.zhouchuanxiang.order.dto.OrderDTO;
import com.zhouchuanxiang.order.feign.StockFeignService;
import com.zhouchuanxiang.order.mapper.OrderMapper;


import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhouchuanxiang
 * @date 2026-05-20
 * @description 订单服务
 */
@Service
public class OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StockFeignService stockFeignService;


    /**
     * 创建订单
     * @return
     */
    //分布式事务注解
    @GlobalTransactional
    public void createOrder() {
        // 插入能否成功？
        OrderDTO order = new OrderDTO();
        order.setProductId(9);
        order.setTotalAmount(1);
        order.setStatus(0);
        orderMapper.insert(order);

        // 扣减库存 能否成功？
        stockFeignService.reduceStock(9);
    }
}

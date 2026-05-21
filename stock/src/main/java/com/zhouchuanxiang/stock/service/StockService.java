package com.zhouchuanxiang.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouchuanxiang.stock.mapper.StockMapper;

/**
 * @author zhouchuanxiang
 * @date 2026-05-20
 * @description 库存服务
 */
@Service
public class StockService {

    @Autowired
    private StockMapper stockMapper;
    
    /**
     * 扣减库存
     */
    public void reduceStock(Integer productId) {
        System.out.println("扣减库存成功，商品ID：" + productId);
        // 扣减库存逻辑
        stockMapper.reduce(productId);
        System.out.println(1/0);
        // ...
        // 扣减库存成功后，返回库存信息
    }
}

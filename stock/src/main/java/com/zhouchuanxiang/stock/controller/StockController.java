package com.zhouchuanxiang.stock.controller;

import com.zhouchuanxiang.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/reduce")
    public String reduceStock(@RequestParam Integer productId) {
        stockService.reduceStock(productId);
        return "reduce stock success";
    }
    

}

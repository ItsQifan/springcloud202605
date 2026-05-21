package com.zhouchuanxiang.stock.mapper;

import com.zhouchuanxiang.stock.dto.StockDTO;

import java.util.List;

public interface StockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockDTO record);

    StockDTO selectByPrimaryKey(Integer id);

    List<StockDTO> selectAll();

    int updateByPrimaryKey(StockDTO record);

    void reduce(Integer productId);
}

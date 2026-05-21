package com.zhouchuanxiang.order.mapper;



import com.zhouchuanxiang.order.dto.OrderDTO;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDTO record);

    OrderDTO selectByPrimaryKey(Integer id);

    List<OrderDTO> selectAll();

    int updateByPrimaryKey(OrderDTO record);
}
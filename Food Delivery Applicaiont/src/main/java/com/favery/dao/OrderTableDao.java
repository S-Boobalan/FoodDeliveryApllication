package com.favery.dao;

import java.util.List;
import com.favery.model.OrderTable;

public interface OrderTableDao {
    int addOrder(OrderTable order);
    OrderTable getOrder(int orderId);
    List<OrderTable> getAllOrders();
}

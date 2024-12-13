package com.favery.dao;

import java.util.List;
import com.favery.model.OrderItem;

public interface OrderItemDao {
    int addOrderItem(OrderItem item);
    OrderItem getOrderItem(int orderItemId);
    List<OrderItem> getAllOrderItems();
    List<OrderItem> getItemsByOrderId(int orderId);
}

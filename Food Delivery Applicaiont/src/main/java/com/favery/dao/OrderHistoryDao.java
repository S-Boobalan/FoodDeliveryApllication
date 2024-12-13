package com.favery.dao;

import java.util.List;
import com.favery.model.OrderHistory;

public interface OrderHistoryDao {
    int addOrderHistory(OrderHistory orderHistory);
    OrderHistory getOrderHistory(int orderHistoryId);
    List<OrderHistory> getAllOrderHistories();
    List<OrderHistory> getAllOrderHistoriesByUserId(int userId);
}

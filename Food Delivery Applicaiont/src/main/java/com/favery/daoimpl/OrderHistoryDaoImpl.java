package com.favery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Import for handling SQL Date type
import java.util.ArrayList;
import java.util.List;

import com.favery.dao.OrderHistoryDao;
import com.favery.model.OrderHistory;
import com.favery.util.MyConnection;

public class OrderHistoryDaoImpl implements OrderHistoryDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet res = null;
    private List<OrderHistory> orderHistoryList = new ArrayList<>();
    private OrderHistory orderHistory = null;

    private static final String INSERT_QUERY = "INSERT INTO `orderhistory` (`orderId`, `userId`, `totalAmount`, `status`) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `orderhistory` WHERE `orderHistoryId` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `orderhistory`";
    private static final String SELECT_BY_USER_ID = "SELECT * FROM `orderhistory` WHERE `userId` = ?";

    int status = 0;

    public OrderHistoryDaoImpl() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public int addOrderHistory(OrderHistory orderHistory) {
        try {
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, orderHistory.getOrderId());
            pstmt.setInt(2, orderHistory.getUserId());
            pstmt.setFloat(3, orderHistory.getTotalAmount());
            pstmt.setString(4, orderHistory.getStatus());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public OrderHistory getOrderHistory(int orderHistoryId) {
        try {
            pstmt = con.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1, orderHistoryId);
            res = pstmt.executeQuery();
            orderHistoryList = extractOrderHistoryFromResultSet(res);
            if (!orderHistoryList.isEmpty()) {
                orderHistory = orderHistoryList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

    @Override
    public List<OrderHistory> getAllOrderHistories() {
        try {
            pstmt = con.prepareStatement(SELECT_ALL_QUERY);
            res = pstmt.executeQuery();
            orderHistoryList = extractOrderHistoryFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
    
    @Override
    public List<OrderHistory> getAllOrderHistoriesByUserId(int userId) {
        List<OrderHistory> userOrderHistoryList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(SELECT_BY_USER_ID);
            pstmt.setInt(1, userId);
            res = pstmt.executeQuery();
            userOrderHistoryList = extractOrderHistoryFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userOrderHistoryList;
    }

    private List<OrderHistory> extractOrderHistoryFromResultSet(ResultSet res) {
        orderHistoryList.clear(); // Clear previous entries before adding new ones
        try {
            while (res.next()) {
                int orderHistoryId = res.getInt("orderHistoryId");
                int orderId = res.getInt("orderId");
                int userId = res.getInt("userId");
                float totalAmount = res.getFloat("totalAmount");
                String status = res.getString("status");
                Date orderDate = res.getDate("orderDate"); // Get the orderDate from the ResultSet

                // Update constructor to include orderDate
                orderHistory = new OrderHistory(orderId, userId, orderDate, totalAmount, status);
                orderHistoryList.add(orderHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
}

package com.favery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.favery.dao.OrderTableDao;
import com.favery.model.OrderTable;
import com.favery.util.MyConnection;

public class OrderTableDaoImpl implements OrderTableDao {
    
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet res = null;
    private List<OrderTable> orderList = new ArrayList<>();
    private OrderTable order = null;

    private static final String INSERT_QUERY = "INSERT INTO `ordertable` (`restaurantId`, `userId`, `totalAmount`, `status`, `paymentMode`) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `ordertable` WHERE `orderId` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `ordertable`";

    int status = 0;

    public OrderTableDaoImpl() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public int addOrder(OrderTable order) {
    	int orderId = 0;
        try {
            pstmt = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, order.getRestaurantId());
            pstmt.setInt(2, order.getUserId());
            pstmt.setFloat(3, order.getTotalAmount());
            pstmt.setString(4, order.getStatus());
            pstmt.setString(5, order.getPaymentMode());
            status = pstmt.executeUpdate();
            if (status > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                    order.setOrderId(orderId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    @Override
    public OrderTable getOrder(int orderId) {
        try {
            pstmt = con.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1, orderId);
            res = pstmt.executeQuery();
            orderList = extractOrderFromResultSet(res);
            if (!orderList.isEmpty()) {
                order = orderList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<OrderTable> getAllOrders() {
        try {
            pstmt = con.prepareStatement(SELECT_ALL_QUERY);
            res = pstmt.executeQuery();
            orderList = extractOrderFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    private List<OrderTable> extractOrderFromResultSet(ResultSet res) {
        try {
            while (res.next()) {
                int orderId = res.getInt("orderId");
                int restaurantId = res.getInt("restaurantId");
                int userId = res.getInt("userId");
                float totalAmount = res.getFloat("totalAmount");
                String status = res.getString("status");
                String paymentMode = res.getString("paymentMode");

                order = new OrderTable(orderId, restaurantId, userId, totalAmount, status, paymentMode);
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}

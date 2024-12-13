package com.favery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.favery.dao.OrderItemDao;
import com.favery.model.OrderItem;
import com.favery.util.MyConnection;

public class OrderItemDaoImpl implements OrderItemDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet res = null;
    private List<OrderItem> orderItemList = new ArrayList<>();
    private OrderItem orderItem = null;

    private static final String INSERT_QUERY = "INSERT INTO `orderitem` (`orderId`, `menuId`, `quantity`, `subTotal`) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `orderitem` WHERE `orderItemId` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `orderitem`";
    private static final String SELECT_BY_ORDER_ID_QUERY = "SELECT * FROM `orderitem` WHERE `orderId` = ?";

    int status = 0;

    public OrderItemDaoImpl() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public int addOrderItem(OrderItem item) {
        try {
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, item.getOrderId());
            pstmt.setInt(2, item.getMenuId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setFloat(4, item.getSubTotal());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        try {
            pstmt = con.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1, orderItemId);
            res = pstmt.executeQuery();
            orderItemList = extractOrderItemFromResultSet(res);
            if (!orderItemList.isEmpty()) {
                orderItem = orderItemList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        try {
            pstmt = con.prepareStatement(SELECT_ALL_QUERY);
            res = pstmt.executeQuery();
            orderItemList = extractOrderItemFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    private List<OrderItem> extractOrderItemFromResultSet(ResultSet res) {
        try {
            while (res.next()) {
                int orderItemId = res.getInt("orderItemId");
                int orderId = res.getInt("orderId");
                int menuId = res.getInt("menuId");
                int quantity = res.getInt("quantity");
                float subTotal = res.getFloat("subTotal");

                orderItem = new OrderItem(orderItemId, orderId, menuId, quantity, subTotal);
                orderItemList.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
    
    @Override
    public List<OrderItem> getItemsByOrderId(int orderId) {
    	List<OrderItem> orderItems = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(SELECT_BY_ORDER_ID_QUERY);
            pstmt.setInt(1, orderId);

            res = pstmt.executeQuery();

            while (res.next()) {
                int orderItemId = res.getInt("orderItemId");
                int menuId = res.getInt("menuId");
                int quantity = res.getInt("quantity");
                float subTotal = res.getFloat("subTotal");

                OrderItem orderItem = new OrderItem(orderItemId, orderId, menuId, quantity, subTotal);
                
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }
    
    
}

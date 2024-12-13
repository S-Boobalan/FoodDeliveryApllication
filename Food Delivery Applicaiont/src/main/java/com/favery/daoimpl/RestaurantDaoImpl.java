package com.favery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.favery.dao.RestaurantDao;
import com.favery.model.Restaurant;
import com.favery.util.MyConnection;

public class RestaurantDaoImpl implements RestaurantDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet res = null;
    private Statement stmt = null;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private Restaurant restaurant = null;

    private static final String INSERT_QUERY = "INSERT INTO `restaurant` (`restaurantName`, `deliveryTime`, `cuisineType`, `address`, `ratings`, `isActive`, `adminId`, `imagePath`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `restaurant` WHERE `restaurantId` = ?";
    private static final String UPDATE_QUERY = "UPDATE `restaurant` SET `restaurantName` = ?, `deliveryTime` = ?, `cuisineType` = ?, `address` = ?, `ratings` = ?, `isActive` = ?, `adminId` = ?, `imagePath` = ? WHERE `restaurantId` = ?";
    private static final String DELETE_QUERY = "DELETE FROM `restaurant` WHERE `restaurantId` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `restaurant`";

    int status = 0;

    public RestaurantDaoImpl() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public int addRestaurant(Restaurant restaurant) {
        try {
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setString(1, restaurant.getRestaurantName());
            pstmt.setInt(2, restaurant.getDeliveryTime());
            pstmt.setString(3, restaurant.getCuisineType());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setFloat(5, restaurant.getRatings());
            pstmt.setBoolean(6, restaurant.isActive());
            pstmt.setInt(7, restaurant.getAdminType());
            pstmt.setString(8, restaurant.getImagePath());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {
        Restaurant restaurant = null;
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_QUERY)) {
            pstmt.setInt(1, restaurantId);
            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    int id = res.getInt("restaurantId");
                    String restaurantName = res.getString("restaurantName");
                    int deliveryTime = res.getInt("deliveryTime");
                    String cuisineType = res.getString("cuisineType");
                    String address = res.getString("address");
                    float ratings = res.getFloat("ratings");
                    boolean isActive = res.getBoolean("isActive");
                    int adminType = res.getInt("adminId");
                    String imagePath = res.getString("imagePath");

                    restaurant = new Restaurant(id, restaurantName, deliveryTime, cuisineType, address, ratings, isActive, adminType, imagePath);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }


    @Override
    public int updateRestaurant(Restaurant restaurant) {
        try {
            pstmt = con.prepareStatement(UPDATE_QUERY);
            pstmt.setString(1, restaurant.getRestaurantName());
            pstmt.setInt(2, restaurant.getDeliveryTime());
            pstmt.setString(3, restaurant.getCuisineType());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setFloat(5, restaurant.getRatings());
            pstmt.setBoolean(6, restaurant.isActive());
            pstmt.setInt(7, restaurant.getAdminType());
            pstmt.setString(8, restaurant.getImagePath());
            pstmt.setInt(9, restaurant.getRestaurantId());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
        return status;
    }

    @Override
    public int deleteRestaurant(int restaurantId) {
        try {
            pstmt = con.prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, restaurantId);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(SELECT_ALL_QUERY);
            restaurantList = extractRestaurantFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    public List<Restaurant> extractRestaurantFromResultSet(ResultSet res) {
        try {
            while (res.next()) {
                int restaurantId = res.getInt("restaurantId");
                String restaurantName = res.getString("restaurantName");
                int deliveryTime = res.getInt("deliveryTime");
                String cuisineType = res.getString("cuisineType");
                String address = res.getString("address");
                float ratings = res.getFloat("ratings");
                boolean isActive = res.getBoolean("isActive");
                int adminType = res.getInt("adminId");
                String imagePath = res.getString("imagePath");

                restaurant = new Restaurant(restaurantId, restaurantName, deliveryTime, cuisineType, address, ratings, isActive, adminType, imagePath);
                restaurantList.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

}

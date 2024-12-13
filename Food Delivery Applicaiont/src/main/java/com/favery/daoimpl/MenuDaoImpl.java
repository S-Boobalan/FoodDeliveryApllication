package com.favery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.favery.dao.MenuDao;
import com.favery.model.Menu;
import com.favery.util.MyConnection;

public class MenuDaoImpl implements MenuDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet res = null;
    private List<Menu> menuList = new ArrayList<>();
    private Menu menu = null;

    private static final String INSERT_QUERY = "INSERT INTO `menu` (`restaurantId`, `menuName`, `price`, `description`, `isAvailable`, `imgPath`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `menu` WHERE `menuId` = ?";
    private static final String UPDATE_QUERY = "UPDATE `menu` SET `restaurantId` = ?, `menuName` = ?, `price` = ?, `description` = ?, `isAvailable` = ?, `imgPath` = ? WHERE `menuId` = ?";
    private static final String DELETE_QUERY = "DELETE FROM `menu` WHERE `menuId` = ?";
    private static final String SELECT_ALL_BY_RESTAURANT_QUERY = "SELECT * FROM `menu` WHERE `restaurantId` = ?";

    int status = 0;

    public MenuDaoImpl() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public int addMenu(Menu menu) {
        try {
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getMenuName());
            pstmt.setFloat(3, menu.getPrice());
            pstmt.setString(4, menu.getDescription());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getImgPath());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    @Override
    public Menu getMenu(int menuId) {
        Menu menu = null;
        try {
            pstmt = con.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1, menuId);
            res = pstmt.executeQuery();
            
            if (res.next()) {
                int restaurantId = res.getInt("restaurantId");
                String menuName = res.getString("menuName");
                float price = res.getFloat("price");
                String description = res.getString("description");
                boolean isAvailable = res.getBoolean("isAvailable");
                String imgPath = res.getString("imgPath");

                menu = new Menu(menuId, restaurantId, menuName, price, description, isAvailable, imgPath);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }
    
    @Override
    public int updateMenu(Menu menu) {
        try {
            pstmt = con.prepareStatement(UPDATE_QUERY);
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getMenuName());
            pstmt.setFloat(3, menu.getPrice());
            pstmt.setString(4, menu.getDescription());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getImgPath());
            pstmt.setInt(7, menu.getMenuId());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteMenu(int menuId) {
        try {
            pstmt = con.prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, menuId);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Menu> getAllMenusByRestaurant(int restaurantId) {
        try {
            pstmt = con.prepareStatement(SELECT_ALL_BY_RESTAURANT_QUERY);
            pstmt.setInt(1, restaurantId);
            res = pstmt.executeQuery();
            menuList = extractMenuFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    private List<Menu> extractMenuFromResultSet(ResultSet res) {
        try {
            while (res.next()) {
                int menuId = res.getInt("menuId");
                int restaurantId = res.getInt("restaurantId");
                String menuName = res.getString("menuName");
                float price = res.getFloat("price");
                String description = res.getString("description");
                boolean isAvailable = res.getBoolean("isAvailable");
                String imgPath = res.getString("imgPath");

                menu = new Menu(menuId, restaurantId, menuName, price, description, isAvailable, imgPath);
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }
}

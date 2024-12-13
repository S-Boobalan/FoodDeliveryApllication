package com.favery.dao;

import java.util.List;
import com.favery.model.Menu;

public interface MenuDao {
    
    int addMenu(Menu menu);
    
    Menu getMenu(int menuId);

    int updateMenu(Menu menu);

    int deleteMenu(int menuId); 

    List<Menu> getAllMenusByRestaurant(int restaurantId);
}

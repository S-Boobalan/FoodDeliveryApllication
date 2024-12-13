package com.favery.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<Integer, CartItem> items;
	
	public Cart() {
		this.items = new LinkedHashMap<Integer, CartItem>();
	}
	
	public void addItem(CartItem item) {
		int menuId = item.getMenuId();
		if(items.containsKey(menuId)) {
			CartItem existingMenu = items.get(menuId);
			existingMenu.setQuantity(existingMenu.getQuantity() + item.getQuantity());
		}
		else {
			items.put(menuId, item);
		}
	}
	
	public void updateItem(int menuId, int quantity) {
		if(items.containsKey(menuId)) {
			if(quantity <= 0) {
				items.remove(menuId);
			}
			else {
				CartItem cartItem = items.get(menuId);
				cartItem.setQuantity(quantity);
				cartItem.setSubTotal((quantity * cartItem.getPrice()));
			}
		}
	}
	
	public void removeItem(int menuId) {
		items.remove(menuId);
	}
	
	public Map<Integer, CartItem> getItems() {
		return items;
	}
	
	public void clear() {
		items.clear();
	}
	
}
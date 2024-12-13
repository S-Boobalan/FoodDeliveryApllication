package com.favery.model;
public class Restaurant {
	private int restaurantId;
	private String restaurantName;
	private int deliveryTime;
	private String cuisineType;
	private String address;
	private float ratings;
	private boolean isActive;
	private int adminType;
	private String imagePath;
	public Restaurant() {
		super();
	}
	public Restaurant(int restaurantId, String restaurantName, int deliveryTime, String cuisineType, String address,
			float ratings, boolean isActive, int adminType, String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.deliveryTime = deliveryTime;
		this.cuisineType = cuisineType;
		this.address = address;
		this.ratings = ratings;
		this.isActive = isActive;
		this.adminType = adminType;
		this.imagePath = imagePath;
	}
	
	public Restaurant(String restaurantName, int deliveryTime, String cuisineType, String address, float ratings,
			boolean isActive, int adminType, String imagePath) {
		super();
		this.restaurantName = restaurantName;
		this.deliveryTime = deliveryTime;
		this.cuisineType = cuisineType;
		this.address = address;
		this.ratings = ratings;
		this.isActive = isActive;
		this.adminType = adminType;
		this.imagePath = imagePath;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getAdminType() {
		return adminType;
	}

	public void setAdminType(int adminType) {
		this.adminType = adminType;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", deliveryTime="
				+ deliveryTime + ", cuisineType=" + cuisineType + ", address=" + address + ", ratings=" + ratings
				+ ", isActive=" + isActive + ", adminType=" + adminType + ", imagePath=" + imagePath + "]";
	}

}

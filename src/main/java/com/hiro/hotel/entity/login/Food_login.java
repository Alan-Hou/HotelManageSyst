package com.hiro.hotel.entity.login;

/**
 * 菜品类
 * @author XX
 *
 */
public class Food_login {
	
	private String foodName; //菜名称
	private double price;	//菜单价
	
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Food_login(){
		
	}
	
	public Food_login(String name, double price){
		this.foodName=name;
		this.price=price;
	}

}

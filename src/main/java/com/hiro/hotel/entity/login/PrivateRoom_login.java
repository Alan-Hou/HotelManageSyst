package com.hiro.hotel.entity.login;

import java.util.ArrayList;

/**
 * 包间类
 * @author XX
 *
 */
public class PrivateRoom_login {
	
	private int roomNumber;		//包间号
	private boolean isEmpty;	//包间的状态，有人或者无人
	private double roomprice;  //包间预定价格
	private ArrayList<Food_login> foodList;  //菜单
	
	public ArrayList<Food_login> getFoodList() {
		return foodList;
	}
	public void setFoodList(ArrayList<Food_login> foodList) {
		this.foodList = foodList;
	}
	public double getRoomprice() {
		return roomprice;
	}
	public void setRoomprice(double roomprice) {
		this.roomprice = roomprice;
	}

	
	
	
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public PrivateRoom_login(){
		
	}
	
	public PrivateRoom_login( int roomNumber, boolean isEmpty, double roomprice){
		this.roomNumber=roomNumber;
		this.isEmpty=isEmpty;
		this.roomprice=roomprice;
	}
	
	public PrivateRoom_login( int roomNumber, boolean isEmpty, double roomprice, ArrayList<Food_login> foodList){
		this.roomNumber=roomNumber;
		this.isEmpty=isEmpty;
		this.roomprice=roomprice;
		this.foodList=foodList;
	}

}

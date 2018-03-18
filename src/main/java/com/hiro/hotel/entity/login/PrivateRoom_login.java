package com.hiro.hotel.entity.login;

import java.util.ArrayList;

/**
 * ������
 * @author XX
 *
 */
public class PrivateRoom_login {
	
	private int roomNumber;		//�����
	private boolean isEmpty;	//�����״̬�����˻�������
	private double roomprice;  //����Ԥ���۸�
	private ArrayList<Food_login> foodList;  //�˵�
	
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

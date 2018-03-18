package com.hiro.hotel.entity.login;

import java.util.ArrayList;

/**
 * @author ZHJ
 * Ԥ���࣬����Ԥ����һЩ��Ϣ
 */
public class Reserve_login {
	
	private int roomNumber;  //Ԥ���ķ����
	private String memberNum; //Ԥ���Ļ�Ա��
	private ArrayList<Food_login> foodList;  //Ԥ���Ĳ˵�
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public ArrayList<Food_login> getFoodList() {
		return foodList;
	}
	public void setFoodList(ArrayList<Food_login> foodList) {
		this.foodList = foodList;
	}
	public String getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}
	
	public Reserve_login(){
		
	}
	
	public Reserve_login(int roomNumber, String memberNum, ArrayList<Food_login> foodlist){
		this.roomNumber=roomNumber;
		this.memberNum=memberNum;
		this.foodList=foodlist;
	}

}

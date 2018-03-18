package com.hiro.hotel.biz.login;

import java.util.ArrayList;
import java.util.Scanner;

import com.hiro.Jdbc.JroomManage_login;
import com.hiro.hotel.entity.login.Food_login;
import com.hiro.hotel.entity.login.PrivateRoom_login;

/**
 * @author XX
 * 包间管理类
 */
public class RoomManage_login {
	
	Scanner input = new Scanner(System.in);
	public static  ArrayList<PrivateRoom_login> rooms = new ArrayList<>();
	public static ArrayList<Food_login> foodList = new ArrayList<>();
	
	
	public static void InitiRoomNumber_login(){

		rooms= JroomManage_login.getRoom();

	}

	/**
	 * 添加包间
	 */
	public void addRoom(){
		System.out.println("请输入要增加的包间号(四位数):");
		int addRoomNumber = input.nextInt();
		System.out.println("请输入该包间的预定价格:");
		double addRoomPrice = input.nextInt();
		rooms.add(new PrivateRoom_login(addRoomNumber,true,addRoomPrice));
        JroomManage_login.addRoom(addRoomNumber,addRoomPrice);
		System.out.println("添加包间成功!");
		
	}

	/**
	 * 查看包间
	 */
	public void SeeRoom_login(){
		System.out.println("房间号\t预定价格\t状态");
		for(int i=0;i<rooms.size(); i++){
			System.out.print(rooms.get(i).getRoomNumber());
			System.out.print("\t");
			System.out.print(rooms.get(i).getRoomprice()+"\t");
			if(rooms.get(i).isEmpty()){
				System.out.println("空");
			}else{
				System.out.println("有人");
			}
		}
	}

	/**
	 * 删除包间
	 */
	public void delRoom(){
		System.out.println("请输入要删除的包间号:");
		int delRoomNumber = input.nextInt();
		if(findRoomByNumber(delRoomNumber)!=null){
			if(findRoomByNumber(delRoomNumber).isEmpty()){
				rooms.remove(findRoomByNumber(delRoomNumber));
				JroomManage_login.delRoom(delRoomNumber);
				System.out.println("删除成功!");
			}else{
				System.out.println("该包间有人，无法删除!");
			}
		}else{
			System.out.println("该包间不存在!");
		}
	}

	/**
	 * 查找包间
	 * @param roomNumber
	 * @return
	 */
	public static PrivateRoom_login findRoomByNumber(int roomNumber){
		for(int i=0; i<rooms.size(); i++){
			if(rooms.get(i).getRoomNumber()==roomNumber){
				return rooms.get(i);
			}
		}
		return null;
	}
}

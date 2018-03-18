package com.hiro.hotel.biz.login;

import java.util.ArrayList;
import java.util.Scanner;

import com.hiro.Jdbc.JroomManage_login;
import com.hiro.hotel.entity.login.Food_login;
import com.hiro.hotel.entity.login.PrivateRoom_login;

/**
 * @author XX
 * ���������
 */
public class RoomManage_login {
	
	Scanner input = new Scanner(System.in);
	public static  ArrayList<PrivateRoom_login> rooms = new ArrayList<>();
	public static ArrayList<Food_login> foodList = new ArrayList<>();
	
	
	public static void InitiRoomNumber_login(){

		rooms= JroomManage_login.getRoom();

	}

	/**
	 * ��Ӱ���
	 */
	public void addRoom(){
		System.out.println("������Ҫ���ӵİ����(��λ��):");
		int addRoomNumber = input.nextInt();
		System.out.println("������ð����Ԥ���۸�:");
		double addRoomPrice = input.nextInt();
		rooms.add(new PrivateRoom_login(addRoomNumber,true,addRoomPrice));
        JroomManage_login.addRoom(addRoomNumber,addRoomPrice);
		System.out.println("��Ӱ���ɹ�!");
		
	}

	/**
	 * �鿴����
	 */
	public void SeeRoom_login(){
		System.out.println("�����\tԤ���۸�\t״̬");
		for(int i=0;i<rooms.size(); i++){
			System.out.print(rooms.get(i).getRoomNumber());
			System.out.print("\t");
			System.out.print(rooms.get(i).getRoomprice()+"\t");
			if(rooms.get(i).isEmpty()){
				System.out.println("��");
			}else{
				System.out.println("����");
			}
		}
	}

	/**
	 * ɾ������
	 */
	public void delRoom(){
		System.out.println("������Ҫɾ���İ����:");
		int delRoomNumber = input.nextInt();
		if(findRoomByNumber(delRoomNumber)!=null){
			if(findRoomByNumber(delRoomNumber).isEmpty()){
				rooms.remove(findRoomByNumber(delRoomNumber));
				JroomManage_login.delRoom(delRoomNumber);
				System.out.println("ɾ���ɹ�!");
			}else{
				System.out.println("�ð������ˣ��޷�ɾ��!");
			}
		}else{
			System.out.println("�ð��䲻����!");
		}
	}

	/**
	 * ���Ұ���
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

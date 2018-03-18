package com.hiro.hotel.biz.login;

import java.util.ArrayList;
import java.util.Scanner;

import com.hiro.Jdbc.Jexpense_login;
import com.hiro.Jdbc.JmemberManage_login;
import com.hiro.Jdbc.JreserveManage_login;
import com.hiro.Jdbc.JroomManage_login;
import com.hiro.hotel.authority.impl.login.DefaultMember_login;
import com.hiro.hotel.entity.login.Expense_login;
import com.hiro.hotel.entity.login.Food_login;
import com.hiro.hotel.entity.login.Reserve_login;
import com.hiro.hotel.test.login.HotelTest_login;

/**
 * @author ZHJ
 * Ԥ���࣬����Ԥ����ص�һЩ����
 */
public class ReserveManage_login {
	Scanner input = new Scanner(System.in);
	static ArrayList<Reserve_login> reserveList = new ArrayList<>();
	MemberManage_login memberManager = new MemberManage_login();
	FoodManage_login foodManager = new FoodManage_login();
	public static void InitiReserve_login(){

		reserveList= JreserveManage_login.getReserve();
	}
	public ReserveManage_login()
	{

	}

	/**
	 * ����Ԥ��
	 */
	public void doReserve(){
		String temp;
		temp=null;
		if(reserveList.size()>=10){ //Ԥ���޶�������
			System.out.println("Ԥ�����������Ѿ��ﵽ����,�޷�����Ԥ��!");
			return ;
		}
		System.out.println("����������ҪԤ���ķ����(1001-1020):");
		int reserveNum = input.nextInt();
		if(RoomManage_login.findRoomByNumber(reserveNum)!=null){
			if(RoomManage_login.findRoomByNumber(reserveNum).isEmpty()){
				System.out.println("��������Ļ�Ա��:");
				String tempMemberNum = input.next();
				if(memberManager.findMemberById_login(tempMemberNum)!=null){
					System.out.println("����Ԥ���ɹ�!\tӦ������:"+RoomManage_login.findRoomByNumber(reserveNum).getRoomprice());
					RoomManage_login.findRoomByNumber(reserveNum).setEmpty(false);//�����ʾΪ����
					JroomManage_login.reserveRoom(reserveNum);
					System.out.println("���Ǳ���Ĳ˵�:");
					foodManager.showFood();
					System.out.println("��������ҪԤ������(y/n)��");
					String answer = input.next();
					ArrayList<Food_login> tempFoodList = new ArrayList<>();
					while(answer.equals("y")){
						System.out.println("����������ҪԤ���Ĳ���:");
						String tempFoodNames = input.next();
						if(temp==null)
						{
							temp=tempFoodNames;
						}
						else
						{
							temp+=" "+tempFoodNames;

						}
						if(foodManager.findFoodByName(tempFoodNames)!=null){
							tempFoodList.add(foodManager.findFoodByName(tempFoodNames));
							System.out.println("���˳ɹ�!");
						}else{
							System.out.println("�Բ�������û�����ֲˣ�����ʧ�ܣ�");
						}
						System.out.println("�Ƿ�Ҫ��������(y/n):");
						answer = input.next();
					}
					reserveList.add(new Reserve_login(reserveNum,tempMemberNum,tempFoodList));
					JreserveManage_login.addReserve(reserveNum,tempMemberNum,temp);
					JroomManage_login.addDish(reserveNum,temp);

					System.out.println("лл���Ĺ���,����Ԥ�����,��ӭ�´�ʹ��!");
				}else{
					System.out.println("�û�Ա�Ų����ڣ�Ԥ��ʧ��!");
				}
			}else{
				System.out.println("�ð�������,Ԥ��ʧ�ܣ�");
			}
		}else{
			System.out.println("������������!");
		}
		
	}

	/**
	 * ����Ԥ��
	 * @param roomNumber
	 * @return
	 */
	public static Reserve_login findReserveByMemberNum(int roomNumber){
		for(int i=0; i<reserveList.size(); i++){
			if(reserveList.get(i).getRoomNumber()==roomNumber){
				return reserveList.get(i);
			}
		}
		return null;
		
	}

	public void returnRoom(){
		System.out.println("��������Ԥ���İ����:");
		int backRoomNumber = input.nextInt();
		if(RoomManage_login.findRoomByNumber(backRoomNumber)!=null){
			if(!RoomManage_login.findRoomByNumber(backRoomNumber).isEmpty()){
				System.out.println("���������Ļ�Ա��:");
				String backMemberNum = input.next();
				if(findReserveByMemberNum(backRoomNumber).getMemberNum().equals(backMemberNum)){
					reserveList.remove(findReserveByMemberNum(backRoomNumber));
					RoomManage_login.findRoomByNumber(backRoomNumber).setEmpty(true);	//�˷��䣬�����ÿ�
					JreserveManage_login.delReserve(backRoomNumber);
					JroomManage_login.retRoom(backRoomNumber);
					System.out.println("�˶��ɹ�!");
				}else{
					System.out.println("Ԥ����������Ա�Ų�ƥ�䣬�˶�ʧ��!");
				}
			}else{
				System.out.println("�ð��䲻��Ԥ�����У��˶�ʧ��!");
			}
		}
		
	}
	
	public void reserveSettle(){
		System.out.println("��������Ԥ���İ����:");
		int tempRoomNumber = input.nextInt();
		if(RoomManage_login.findRoomByNumber(tempRoomNumber)!=null){
			if(!RoomManage_login.findRoomByNumber(tempRoomNumber).isEmpty()){
				System.out.println("���������Ļ�Ա��");
				String tempMemberId = input.next();
				if(memberManager.findMemberById_login(tempMemberId)!=null 
						&& ReserveManage_login.findReserveByMemberNum(tempRoomNumber).getMemberNum().equals(tempMemberId)){
					System.out.println("���Ĳ˵���:");
					double sum = 0;
					for(int i=0; i<ReserveManage_login.findReserveByMemberNum(tempRoomNumber).getFoodList().size(); i++){
						System.out.print(ReserveManage_login.findReserveByMemberNum(tempRoomNumber).getFoodList().get(i).getFoodName()+"��");
						sum+=ReserveManage_login.findReserveByMemberNum(tempRoomNumber).getFoodList().get(i).getPrice();
					}
					sum+=RoomManage_login.findRoomByNumber(tempRoomNumber).getRoomprice();
					System.out.println("\n��һ����Ҫ֧��:"+sum+"Ԫ");
					reserveList.remove(findReserveByMemberNum(tempRoomNumber));
					RoomManage_login.findRoomByNumber(tempRoomNumber).setEmpty(true);	//�˷��䣬�����ÿ�
					JreserveManage_login.delReserve(tempRoomNumber);
					JroomManage_login.retRoom(tempRoomNumber);
					ExpenseManage_login.expenseManage.add(new Expense_login("Ԥ���������ѽ���",sum));  //���뵽������ˮ
					Jexpense_login.addExpense("Ԥ���������ѽ���",sum);
					DefaultMember_login me = new DefaultMember_login();
					me.Integral(JmemberManage_login.searchRet(tempMemberId).getMemberName(), sum);
				}else{
					System.out.println("���Ļ�Ա�Ų����ڻ��߻�Ա���뷿��Ų�ƥ�䣬����ʧ��!");
				}
			}else{
				System.out.println("�ð����ǿյģ�����ʧ��!");
			}
		}else{
			System.out.println("������������!");
		}
			
	}
	
	public void showReserveList(){
		System.out.println("Ԥ������\tԤ����Ա\tԤ���˵�");
		for(int i=0; i<reserveList.size(); i++){
			System.out.print(reserveList.get(i).getRoomNumber()+"\t"+reserveList.get(i).getMemberNum()+"\t");
			for(int j=0; j<reserveList.get(i).getFoodList().size(); j++){
				System.out.print(reserveList.get(i).getFoodList().get(j).getFoodName()+"��");
			}
		}
		System.out.println();
	}
}

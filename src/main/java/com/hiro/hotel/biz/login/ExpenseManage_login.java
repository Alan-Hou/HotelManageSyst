package com.hiro.hotel.biz.login;

import java.util.ArrayList;
import java.util.Scanner;

import com.hiro.Jdbc.Jexpense_login;
import com.hiro.hotel.entity.login.Expense_login;

/**
 * @author XX
 * ���ù�����
 */
public class ExpenseManage_login {
	
	Scanner input = new Scanner(System.in);
	
	public static ArrayList<Expense_login> expenseManage = new ArrayList<>();
	MemberManage_login memberManager = new MemberManage_login();
	public static void initExpense_login()
	{
		expenseManage=Jexpense_login.getExpense();
	}

	/**
	 * ��������
	 */
	public void income(){
		System.out.println("������������:");
		double tempMoney = input.nextDouble();
		System.out.println("���������뱸ע:");
		String tempRemarks = input.next();
		expenseManage.add(new Expense_login(tempRemarks,tempMoney));
		Jexpense_login.addExpense(tempRemarks,tempMoney);
		System.out.println("�����ˮ�ɹ�!");
	}

	/**
	 * ����֧��
	 */
	public void expenditure(){
		System.out.println("������֧�����:");
		double tempMoney = input.nextDouble();
		System.out.println("������֧����ע:");
		String tempRemarks = input.next();
		expenseManage.add(new Expense_login(tempRemarks,tempMoney));
		Jexpense_login.addExpense(tempRemarks,tempMoney);
		System.out.println("�����ˮ�ɹ�!");
	}

	/**
	 * Ԥ������
	 */
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
					System.out.println("\n��һ����Ҫ֧��:"+sum+"Ԫ");
					RoomManage_login.findRoomByNumber(tempRoomNumber).setEmpty(true);	//�˷��䣬�����ÿ�
					expenseManage.add(new Expense_login("Ԥ���������ѽ���",sum));  //���뵽������ˮ
					Jexpense_login.addExpense("Ԥ���������ѽ���",sum);

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
	
	public void showRunningAccount(){
		System.out.println("��ˮ���\t��ˮ��ע");
		for(int i=0; i<expenseManage.size(); i++){
			System.out.println(expenseManage.get(i).getTotalMoney()+"\t"+expenseManage.get(i).getRemarks());
		}
	}

}

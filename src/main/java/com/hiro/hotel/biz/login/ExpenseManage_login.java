package com.hiro.hotel.biz.login;

import java.util.ArrayList;
import java.util.Scanner;

import com.hiro.Jdbc.Jexpense_login;
import com.hiro.hotel.entity.login.Expense_login;

/**
 * @author XX
 * 费用管理类
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
	 * 费用收入
	 */
	public void income(){
		System.out.println("请输入收入金额:");
		double tempMoney = input.nextDouble();
		System.out.println("请输入收入备注:");
		String tempRemarks = input.next();
		expenseManage.add(new Expense_login(tempRemarks,tempMoney));
		Jexpense_login.addExpense(tempRemarks,tempMoney);
		System.out.println("添加流水成功!");
	}

	/**
	 * 费用支出
	 */
	public void expenditure(){
		System.out.println("请输入支出金额:");
		double tempMoney = input.nextDouble();
		System.out.println("请输入支出备注:");
		String tempRemarks = input.next();
		expenseManage.add(new Expense_login(tempRemarks,tempMoney));
		Jexpense_login.addExpense(tempRemarks,tempMoney);
		System.out.println("添加流水成功!");
	}

	/**
	 * 预订结算
	 */
	public void reserveSettle(){
		System.out.println("请输入您预定的包间号:");
		int tempRoomNumber = input.nextInt();
		if(RoomManage_login.findRoomByNumber(tempRoomNumber)!=null){
			if(!RoomManage_login.findRoomByNumber(tempRoomNumber).isEmpty()){
				System.out.println("请输入您的会员号");
				String tempMemberId = input.next();
				if(memberManager.findMemberById_login(tempMemberId)!=null 
						&& ReserveManage_login.findReserveByMemberNum(tempRoomNumber).getMemberNum().equals(tempMemberId)){
					System.out.println("您的菜单是:");
					double sum = 0;
					for(int i=0; i<ReserveManage_login.findReserveByMemberNum(tempRoomNumber).getFoodList().size(); i++){
						System.out.print(ReserveManage_login.findReserveByMemberNum(tempRoomNumber).getFoodList().get(i).getFoodName()+"、");
						sum+=ReserveManage_login.findReserveByMemberNum(tempRoomNumber).getFoodList().get(i).getPrice();
					}
					System.out.println("\n您一共需要支付:"+sum+"元");
					RoomManage_login.findRoomByNumber(tempRoomNumber).setEmpty(true);	//退房间，房间置空
					expenseManage.add(new Expense_login("预定包间消费结账",sum));  //加入到结账流水
					Jexpense_login.addExpense("预定包间消费结账",sum);

				}else{
					System.out.println("您的会员号不存在或者会员号与房间号不匹配，结算失败!");
				}
			}else{
				System.out.println("该包间是空的，结算失败!");
			}
		}else{
			System.out.println("包间号输入错误!");
		}
			
	}
	
	public void showRunningAccount(){
		System.out.println("流水金额\t流水备注");
		for(int i=0; i<expenseManage.size(); i++){
			System.out.println(expenseManage.get(i).getTotalMoney()+"\t"+expenseManage.get(i).getRemarks());
		}
	}

}

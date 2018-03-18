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
 * 预订类，包含预订相关的一些操作
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
	 * 进行预订
	 */
	public void doReserve(){
		String temp;
		temp=null;
		if(reserveList.size()>=10){ //预订限定包间数
			System.out.println("预定包间人数已经达到上限,无法继续预定!");
			return ;
		}
		System.out.println("请输入你想要预定的房间号(1001-1020):");
		int reserveNum = input.nextInt();
		if(RoomManage_login.findRoomByNumber(reserveNum)!=null){
			if(RoomManage_login.findRoomByNumber(reserveNum).isEmpty()){
				System.out.println("请输入你的会员号:");
				String tempMemberNum = input.next();
				if(memberManager.findMemberById_login(tempMemberNum)!=null){
					System.out.println("包间预订成功!\t应付订金:"+RoomManage_login.findRoomByNumber(reserveNum).getRoomprice());
					RoomManage_login.findRoomByNumber(reserveNum).setEmpty(false);//包间表示为有人
					JroomManage_login.reserveRoom(reserveNum);
					System.out.println("这是本店的菜单:");
					foodManager.showFood();
					System.out.println("请问您需要预订菜吗(y/n)？");
					String answer = input.next();
					ArrayList<Food_login> tempFoodList = new ArrayList<>();
					while(answer.equals("y")){
						System.out.println("请输入您需要预订的菜名:");
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
							System.out.println("订菜成功!");
						}else{
							System.out.println("对不起，我们没有这种菜，订菜失败！");
						}
						System.out.println("是否要继续订菜(y/n):");
						answer = input.next();
					}
					reserveList.add(new Reserve_login(reserveNum,tempMemberNum,tempFoodList));
					JreserveManage_login.addReserve(reserveNum,tempMemberNum,temp);
					JroomManage_login.addDish(reserveNum,temp);

					System.out.println("谢谢您的光临,本次预定完成,欢迎下次使用!");
				}else{
					System.out.println("该会员号不存在，预定失败!");
				}
			}else{
				System.out.println("该包间有人,预定失败！");
			}
		}else{
			System.out.println("包间号输入错误!");
		}
		
	}

	/**
	 * 查找预订
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
		System.out.println("请输入您预定的包间号:");
		int backRoomNumber = input.nextInt();
		if(RoomManage_login.findRoomByNumber(backRoomNumber)!=null){
			if(!RoomManage_login.findRoomByNumber(backRoomNumber).isEmpty()){
				System.out.println("请输入您的会员号:");
				String backMemberNum = input.next();
				if(findReserveByMemberNum(backRoomNumber).getMemberNum().equals(backMemberNum)){
					reserveList.remove(findReserveByMemberNum(backRoomNumber));
					RoomManage_login.findRoomByNumber(backRoomNumber).setEmpty(true);	//退房间，房间置空
					JreserveManage_login.delReserve(backRoomNumber);
					JroomManage_login.retRoom(backRoomNumber);
					System.out.println("退订成功!");
				}else{
					System.out.println("预订包间号与会员号不匹配，退订失败!");
				}
			}else{
				System.out.println("该包间不在预定序列，退订失败!");
			}
		}
		
	}
	
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
					sum+=RoomManage_login.findRoomByNumber(tempRoomNumber).getRoomprice();
					System.out.println("\n您一共需要支付:"+sum+"元");
					reserveList.remove(findReserveByMemberNum(tempRoomNumber));
					RoomManage_login.findRoomByNumber(tempRoomNumber).setEmpty(true);	//退房间，房间置空
					JreserveManage_login.delReserve(tempRoomNumber);
					JroomManage_login.retRoom(tempRoomNumber);
					ExpenseManage_login.expenseManage.add(new Expense_login("预定包间消费结账",sum));  //加入到结账流水
					Jexpense_login.addExpense("预定包间消费结账",sum);
					DefaultMember_login me = new DefaultMember_login();
					me.Integral(JmemberManage_login.searchRet(tempMemberId).getMemberName(), sum);
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
	
	public void showReserveList(){
		System.out.println("预定房号\t预定会员\t预定菜单");
		for(int i=0; i<reserveList.size(); i++){
			System.out.print(reserveList.get(i).getRoomNumber()+"\t"+reserveList.get(i).getMemberNum()+"\t");
			for(int j=0; j<reserveList.get(i).getFoodList().size(); j++){
				System.out.print(reserveList.get(i).getFoodList().get(j).getFoodName()+"、");
			}
		}
		System.out.println();
	}
}

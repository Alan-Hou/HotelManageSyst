package com.hiro.hotel.authority.impl.login;

import com.hiro.Jdbc.Jexpense_login;
import com.hiro.Jdbc.JmemberManage_login;
import com.hiro.Jdbc.JroomManage_login;
import com.hiro.authority.login.NomalDo_login;
import com.hiro.hotel.biz.login.ExpenseManage_login;
import com.hiro.hotel.biz.login.FoodManage_login;
import com.hiro.hotel.biz.login.MemberManage_login;
import com.hiro.hotel.biz.login.RoomManage_login;
import com.hiro.hotel.entity.login.Expense_login;
import com.hiro.hotel.entity.login.Food_login;
import com.hiro.hotel.entity.login.Member_login;
import com.hiro.hotel.test.login.HotelTest_login;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author ZHJ
 * 用户的操作
 */
public class DefaultNomalDo_login implements NomalDo_login {

    Scanner input = new Scanner(System.in);
    FoodManage_login foodManager = new FoodManage_login();

    //消费查询
    @Override
    public void ConsuQuery() {
        System.out.println("请输入您的包间号:");
        int roomNumber = input.nextInt();
        if (RoomManage_login.findRoomByNumber(roomNumber) != null) {
            if (!RoomManage_login.findRoomByNumber(roomNumber).isEmpty()) {
                double sum = 0;
                if (RoomManage_login.findRoomByNumber(roomNumber).getFoodList()!= null) {
                    for (int i = 0; i < RoomManage_login.findRoomByNumber(roomNumber).getFoodList().size(); i++) {
                        sum += RoomManage_login.findRoomByNumber(roomNumber).getFoodList().get(i).getPrice();
                    }
                }
                System.out.println("您目前一共消费：" + sum + "元");
            } else {
                System.out.println("该包间是空的,查询失败!");
            }
        } else {
            System.out.println("房间号不存在！");
        }

    }

    //查看账单
    @Override
    public void showBill() {

        System.out.println("请输入您的包间号:");
        int roomNumber = input.nextInt();
        if (RoomManage_login.findRoomByNumber(roomNumber) != null) {
            if (!RoomManage_login.findRoomByNumber(roomNumber).isEmpty()) {
                System.out.println("包间号为" + roomNumber + "的消费账单：");
                if (RoomManage_login.findRoomByNumber(roomNumber).getFoodList() != null) {
                    for (int i = 0; i < RoomManage_login.findRoomByNumber(roomNumber).getFoodList().size(); i++) {
                        if (RoomManage_login.findRoomByNumber(roomNumber).getFoodList().get(i).getFoodName() != null) {
                            System.out.println(RoomManage_login.findRoomByNumber(roomNumber).getFoodList().get(i).getFoodName() + "\t"
                                    + RoomManage_login.findRoomByNumber(roomNumber).getFoodList().get(i).getPrice());
                        }
                    }
                }
            } else {
                System.out.println("该包间是空的!");
            }
        } else {
            System.out.println("房间号不存在！");
        }
    }

    //点菜
    @Override
    public void oderFood() {
        System.out.println("请输入您的包间号:");
        int roomNumber = input.nextInt();
        if (RoomManage_login.findRoomByNumber(roomNumber) != null) {
            if (!RoomManage_login.findRoomByNumber(roomNumber).isEmpty()) {
                System.out.println("这是本店的菜单:");
                foodManager.showFood();
                System.out.println("请问您需要点菜吗(y/n)？");
                String answer = input.next();
                ArrayList<Food_login> tempFoodList = new ArrayList<>();
                while (answer.equals("y")) {
                    System.out.println("请输入您需要预订的菜名:");
                    String tempFoodNames = input.next();
                    if (foodManager.findFoodByName(tempFoodNames) != null) {
                        tempFoodList.add(foodManager.findFoodByName(tempFoodNames));
                        JroomManage_login.addDish(roomNumber, tempFoodNames);
                        System.out.println("点菜成功!");
                    } else {
                        System.out.println("对不起，我们没有这种菜，订点菜失败！");
                    }
                    System.out.println("是否要继续点菜(y/n):");
                    answer = input.next();
                }
                System.out.println("点菜成功，请您稍等，您的菜马上就到!");
                RoomManage_login.findRoomByNumber(roomNumber).setFoodList(tempFoodList);
            } else {
                System.out.println("对不起，该包间是空的，无法点菜!");
            }
        } else {
            System.out.println("包间号输入错误!");
        }

    }
    //结账
    @Override
    public void checkout() {
        System.out.println("请输入您的包间号:");
        int roomNumber = input.nextInt();
        double sum = 0;
        if (RoomManage_login.findRoomByNumber(roomNumber) != null) {
            if (!RoomManage_login.findRoomByNumber(roomNumber).isEmpty()) {
                if (RoomManage_login.findRoomByNumber(roomNumber).getFoodList() != null) {
                    for (int i = 0; i < RoomManage_login.findRoomByNumber(roomNumber).getFoodList().size(); i++) {
                        sum += RoomManage_login.findRoomByNumber(roomNumber).getFoodList().get(i).getPrice();
                    }
                }
                sum += RoomManage_login.findRoomByNumber(roomNumber).getRoomprice();
                System.out.println("您此次一共需要支付:" + sum + "元");
                RoomManage_login.findRoomByNumber(roomNumber).setEmpty(true);    //结账后自动退还包间
                ExpenseManage_login.expenseManage.add(new Expense_login("用户自行结算", sum));
                JroomManage_login.retRoom(roomNumber);
                Jexpense_login.addExpense("用户自行结算", sum);
                DefaultMember_login me = new DefaultMember_login();
                me.Integral(HotelTest_login.name, sum);
            } else {
                System.out.println("该包间是空的！");
            }
        } else {
            System.out.println("该包间不存在!");
        }
    }

    //开包间
    @Override
    public void getRoom() {
        System.out.println("请输入你需要开的包间号:");
        int roomNumber = input.nextInt();
        if (RoomManage_login.findRoomByNumber(roomNumber) != null) {
            if (RoomManage_login.findRoomByNumber(roomNumber).isEmpty()) {
                System.out.println("开包间成功!");
                RoomManage_login.findRoomByNumber(roomNumber).setEmpty(false);
                JroomManage_login.reserveRoom(roomNumber);
            } else {
                System.out.println("对不起，无此包间已经有人,开包间失败!");
            }
        } else {
            System.out.println("包间号输入错误!");
        }

    }

    //查看包间状态
    @Override
    public void seeRoom() {
        System.out.println("房间号\t预定价格\t状态");
        for (int i = 0; i < RoomManage_login.rooms.size(); i++) {
            System.out.print(RoomManage_login.rooms.get(i).getRoomNumber());
            System.out.print("\t");
            System.out.print(RoomManage_login.rooms.get(i).getRoomprice() + "\t");
            if (RoomManage_login.rooms.get(i).isEmpty()) {
                System.out.println("空");
            } else {
                System.out.println("有人");
            }
        }

    }

    //修改密码
    @Override
    public void rePass() {
        Member_login member = new Member_login();
        MemberManage_login manage = new MemberManage_login();
        String number;
        number = null;
        String newpassword;
        newpassword = null;
        String password;
        password = null;
        System.out.println("请输入你的会员号");
        number = input.next();
        member = manage.findMemberById_login(number);
        if (member == null) {
            System.out.println("该会员不存在");
        } else {
            System.out.println("请输入密码");
            password = input.next();
            if (member.getPassword().equals(password)) {
                System.out.println("请输入你的新密码");
                newpassword = input.next();
                member.setPassword(newpassword);
                JmemberManage_login.rePass(number, newpassword);
                System.out.println("修改成功!");
            } else {
                System.out.println("密码错误");
            }
        }


    }


}

package com.hiro.hotel.test.login;

import com.hiro.Jdbc.Jexpense_login;
import com.hiro.hotel.authority.impl.login.DefaultMember_login;
import com.hiro.hotel.authority.impl.login.DefaultNomalDo_login;
import com.hiro.hotel.biz.login.*;
import com.hiro.hotel.entity.login.Food_login;
import com.hiro.hotel.entity.login.Manager_login;
import com.hiro.hotel.role.login.Role;
import com.hiro.hotel.role.login.User;

import java.util.Scanner;

public class HotelTest_login {
    public static String name;
    Scanner input = new Scanner(System.in);
    MemberManage_login memberManage = new MemberManage_login();



    public static void main(String[] args) {
        HotelTest_login test = new HotelTest_login();
        Manager_login manager = new Manager_login();
        User user = new User();
        RoomManage_login.InitiRoomNumber_login();
        FoodManage_login.IniFood();
        MemberManage_login.IniMembers();
        ReserveManage_login.InitiReserve_login();
        ExpenseManage_login.initExpense_login();
        System.out.println("****************欢迎使用酒楼管理系统****************");
        test.accreditRole(user);
        if (user.getRole().getDescription().equals("manager")) {
            test.doManage(user);
        } else {
            test.doNomal(user);
        }

    }
    /**
     *  author ZHJ
     *  给登陆的用户一个身份，要么是管理员，要么是用户
     * @param user
     */
    private void accreditRole(User user) {
        System.out.print("请输入用户名：");

        String username = input.next();
        System.out.print("请输入密码：");
        String password = input.next();
        if (user.login(username, password)) {
            if (username.equals("admin")) {
                user.setRole(new Role("manager"));
            } else {
                user.setRole(new Role("nomal"));// 为用户授权角色
            }
            System.out.println("登录成功！");
            name=username;
        } else {
            System.out.println("用户名或者密码错误,登录失败,请重新登录");
            accreditRole(user);
        }
    }

    private void doNomal(User user) {
        DefaultNomalDo_login nomal = new DefaultNomalDo_login();
        System.out.println("****************欢迎使用酒楼用户登录系统****************");
        System.out.println("1.查看包间\t2.现开包间\t3.消费查询\t4.查看账单\t5.点菜\t6.结账\t7.修改密码\t8.退出");
        System.out.println("请选择要进行的操作:");
        int chooseAc = input.nextInt();

        try {
            switch (chooseAc) {
                case 1:
                    nomal.seeRoom();
                    System.out.println("\n\n");
                    doNomal(user);
                    break;
                case 2:
                    nomal.getRoom();
                    System.out.println("\n\n");
                    doNomal(user);
                    break;
                case 3:
                    nomal.ConsuQuery();
                    System.out.println("\n\n");
                    doNomal(user);
                    break;
                case 4:
                    nomal.showBill();
                    System.out.println("\n\n");
                    doNomal(user);
                    break;
                case 5:
                    nomal.oderFood();
                    System.out.println("\n\n");
                    doNomal(user);
                    break;
                case 6:
                    nomal.checkout();
                    System.out.println("\n\n");
                    doNomal(user);
                    break;
                case 7:
                    nomal.rePass();
                    System.out.println("\n\n");
                    doNomal(user);
                    break;
                case 8:
                    System.out.println("欢迎下次使用!");
                    System.exit(0);
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请输入正确格式的数字1-7");
            System.out.println("\n\n");
            doNomal(user);
        }
    }

    /**
     * XX
     * 管理员登录界面
     * @param user
     */
    private void doManage(User user) {
        System.out.println("****************欢迎使用酒楼管理员登录系统****************");
        System.out.println("1.包间管理\t2.菜品管理\t3.会员管理\t4.预订管理\t5.费用管理\t6.退出");
        System.out.println("请选择要进行的操作:");

        int chooseAc = input.nextInt();
        try {
            switch (chooseAc) {
                case 1:
                    RoomManage_login roomManage = new RoomManage_login();
                    RoomManage(roomManage);
                    System.out.println("\n\n");
                    doManage(user);
                    break;
                case 2:
                    FoodManage_login foodManage = new FoodManage_login();
                    FoodManage(foodManage);
                    System.out.println("\n\n");
                    doManage(user);
                    break;
                case 3:
                    DefaultMember_login memberManage = new DefaultMember_login();
                    MemberManage(memberManage);
                    System.out.println("\n\n");
                    doManage(user);
                    break;
                case 4:
                    ReserveManage_login reserveManage = new ReserveManage_login();
                    ReserveManage(reserveManage);
                    System.out.println("\n\n");
                    doManage(user);
                    break;
                case 5:
                    ExpenseManage_login expenseManage = new ExpenseManage_login();
                    ExpenseManage(expenseManage);
                    System.out.println("\n\n");
                    doManage(user);
                    break;
                case 6:
                    System.out.println("欢迎下次使用!");
                    System.exit(0);
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请输入正确格式的数字1-6");
            System.out.println("\n\n");
            doManage(user);
        }

    }

    /**
     * XX
     * 费用管理
     * @param expenseManage
     */
    private void ExpenseManage(ExpenseManage_login expenseManage) {
        System.out.println("******************欢迎使用费用管理****************");
        System.out.println("1.添加支出\t2.添加收入\t3.查看流水\t4.返回");
        System.out.println("请选择要进行的操作:");
        int choose = input.nextInt();
        try {
            switch (choose) {
                case 1:
                    expenseManage.expenditure();//添加支出
                    System.out.println("\n\n");
                    ExpenseManage(expenseManage);
                    break;
                case 2:
                    expenseManage.income();  //添加收入
                    System.out.println("\n\n");
                    ExpenseManage(expenseManage);
                    break;
                case 3:
                    expenseManage.showRunningAccount();
                    System.out.println("\n\n");
                    ExpenseManage(expenseManage);
                    break;
                case 4:
                    User user = new User();
                    System.out.println("\n\n");
                    doManage(user);
                    break;
                default:
                    throw new Exception();

            }
        } catch (Exception e) {
            System.out.println("请输入正确格式的数字1-4");
            System.out.println("\n\n");
            ExpenseManage(expenseManage);
        }
    }

    /**
     * XX
     * 预订管理
     * @param reserveManage
     */
    private void ReserveManage(ReserveManage_login reserveManage) {
        System.out.println("******************欢迎进行预定管理****************");
        System.out.println("1.预定包间\t2.删除预定\t3.查看预订\t4.预定结算\t5.返回");
        System.out.println("请选择要进行的操作:");
        int choose = input.nextInt();
        try {
            switch (choose) {
                case 1:
                    reserveManage.doReserve();
                    System.out.println("\n\n");
                    ReserveManage(reserveManage);
                    break;
                case 2:
                    reserveManage.returnRoom();
                    System.out.println("\n\n");
                    ReserveManage(reserveManage);
                    break;
                case 3:
                    reserveManage.showReserveList();
                    System.out.println("\n\n");
                    ReserveManage(reserveManage);
                    break;
                case 4:
                    reserveManage.reserveSettle();
                    System.out.println("\n\n");
                    ReserveManage(reserveManage);
                    break;
                case 5:
                    User user = new User();
                    System.out.println("\n\n");
                    doManage(user);
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请输入正确格式的数字1-5");
            System.out.println("\n\n");
            ReserveManage(reserveManage);
        }
    }

    /**
     * XX
     * 预订管理
     * @param memberManage
     */
    private void MemberManage(DefaultMember_login memberManage) {
        System.out.println("***********欢迎进行会员管理*************");

        System.out.println("1.注册会员\t2.查找会员\t3.删除会员\t4.查看会员\t5.修改会员积分\t6.返回");
        System.out.println("请选择要进行的操作:");
        int choose = input.nextInt();
        try {
            switch (choose) {
                case 1:
                    memberManage.create_login();
                    System.out.println("\n\n");
                    MemberManage(memberManage);
                    break;
                case 2:
                    memberManage.searchMember();
                    System.out.println("\n\n");
                    MemberManage(memberManage);
                    break;
                case 3:
                    memberManage.delMember_login();
                    System.out.println("\n\n");
                    MemberManage(memberManage);
                    break;
                case 4:
                    memberManage.seeMember_login();
                    System.out.println("\n\n");
                    MemberManage(memberManage);
                    break;
                case 5:
                    memberManage.modifyIntegral();
                    System.out.println("\n\n");
                    MemberManage(memberManage);
                    break;
                case 6:
                    User user = new User();
                    System.out.println("\n\n");
                    doManage(user);
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请输入正确格式的数字(1-6)");
            System.out.println("\n\n");
            MemberManage(memberManage);
        }
    }

    /**
     * XX
     * 菜品管理
     * @param foodManage
     */
    private void FoodManage(FoodManage_login foodManage) {
        System.out.println("***********欢迎进行菜品管理*************");

        System.out.println("1.查看菜单\t2.添加新菜\t3.删除旧菜\t4.查找某菜\t5.返回");
        System.out.println("请选择要进行的操作:");
        int choose = input.nextInt();
        try {
            switch (choose) {
                case 1:
                    foodManage.showFood();
                    System.out.println("\n\n");
                    FoodManage(foodManage);
                    break;
                case 2:
                    foodManage.addFood();
                    System.out.println("\n\n");
                    FoodManage(foodManage);
                    break;
                case 3:
                    foodManage.delFood();
                    System.out.println("\n\n");
                    FoodManage(foodManage);
                    break;
                case 4:
                    System.out.println("请输入要查找的菜名:");
                    String findFoodName = input.next();
                    Food_login findFood = foodManage.findFoodByName(findFoodName);
                    if (findFood != null) {
                        System.out.println("您要查找的菜是:" + findFood.getFoodName() + ",它的销售价格是:" + findFood.getPrice());
                    } /*else {
                        System.out.println("对不起，菜单中不存在这种菜!");
                    }*/
                    System.out.println("\n\n");
                    FoodManage(foodManage);
                    break;
                case 5:
                    User user = new User();
                    System.out.println("\n\n");
                    doManage(user);
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("请输入正确格式的数字(1-5)");
            System.out.println("\n\n");
            FoodManage(foodManage);
        }
    }

    /**
     * XX
     * 对餐馆的包间进行管理
     * @param roomManage
     */
    public void RoomManage(RoomManage_login roomManage) {
        System.out.println("***********欢迎进行包间管理*************");

        System.out.println("1.查看包间\t2.增加包间\t3.删除包间\t4.返回");
        System.out.println("请选择要进行的操作:");
        int choose = input.nextInt();
        try {
            switch (choose) {
                case 1:
                    System.out.println("查看包间状态");
                    roomManage.SeeRoom_login();
                    System.out.println("\n\n");
                    RoomManage(roomManage);
                    break;
                case 2:
                    System.out.println("增加包间");
                    roomManage.addRoom();
                    System.out.println("\n\n");
                    RoomManage(roomManage);
                    break;
                case 3:
                    System.out.println("删除包间");
                    roomManage.delRoom();
                    System.out.println("\n\n");
                    RoomManage(roomManage);
                    break;
                case 4:
                    User user = new User();
                    System.out.println("\n\n");
                    doManage(user);
                    break;
                default:
                    throw new Exception();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请输入正确格式的数字(1-4)");
            RoomManage(roomManage);
        }
    }


}

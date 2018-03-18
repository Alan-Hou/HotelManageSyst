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
        System.out.println("****************��ӭʹ�þ�¥����ϵͳ****************");
        test.accreditRole(user);
        if (user.getRole().getDescription().equals("manager")) {
            test.doManage(user);
        } else {
            test.doNomal(user);
        }

    }
    /**
     *  author ZHJ
     *  ����½���û�һ����ݣ�Ҫô�ǹ���Ա��Ҫô���û�
     * @param user
     */
    private void accreditRole(User user) {
        System.out.print("�������û�����");

        String username = input.next();
        System.out.print("���������룺");
        String password = input.next();
        if (user.login(username, password)) {
            if (username.equals("admin")) {
                user.setRole(new Role("manager"));
            } else {
                user.setRole(new Role("nomal"));// Ϊ�û���Ȩ��ɫ
            }
            System.out.println("��¼�ɹ���");
            name=username;
        } else {
            System.out.println("�û��������������,��¼ʧ��,�����µ�¼");
            accreditRole(user);
        }
    }

    private void doNomal(User user) {
        DefaultNomalDo_login nomal = new DefaultNomalDo_login();
        System.out.println("****************��ӭʹ�þ�¥�û���¼ϵͳ****************");
        System.out.println("1.�鿴����\t2.�ֿ�����\t3.���Ѳ�ѯ\t4.�鿴�˵�\t5.���\t6.����\t7.�޸�����\t8.�˳�");
        System.out.println("��ѡ��Ҫ���еĲ���:");
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
                    System.out.println("��ӭ�´�ʹ��!");
                    System.exit(0);
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("��������ȷ��ʽ������1-7");
            System.out.println("\n\n");
            doNomal(user);
        }
    }

    /**
     * XX
     * ����Ա��¼����
     * @param user
     */
    private void doManage(User user) {
        System.out.println("****************��ӭʹ�þ�¥����Ա��¼ϵͳ****************");
        System.out.println("1.�������\t2.��Ʒ����\t3.��Ա����\t4.Ԥ������\t5.���ù���\t6.�˳�");
        System.out.println("��ѡ��Ҫ���еĲ���:");

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
                    System.out.println("��ӭ�´�ʹ��!");
                    System.exit(0);
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("��������ȷ��ʽ������1-6");
            System.out.println("\n\n");
            doManage(user);
        }

    }

    /**
     * XX
     * ���ù���
     * @param expenseManage
     */
    private void ExpenseManage(ExpenseManage_login expenseManage) {
        System.out.println("******************��ӭʹ�÷��ù���****************");
        System.out.println("1.���֧��\t2.�������\t3.�鿴��ˮ\t4.����");
        System.out.println("��ѡ��Ҫ���еĲ���:");
        int choose = input.nextInt();
        try {
            switch (choose) {
                case 1:
                    expenseManage.expenditure();//���֧��
                    System.out.println("\n\n");
                    ExpenseManage(expenseManage);
                    break;
                case 2:
                    expenseManage.income();  //�������
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
            System.out.println("��������ȷ��ʽ������1-4");
            System.out.println("\n\n");
            ExpenseManage(expenseManage);
        }
    }

    /**
     * XX
     * Ԥ������
     * @param reserveManage
     */
    private void ReserveManage(ReserveManage_login reserveManage) {
        System.out.println("******************��ӭ����Ԥ������****************");
        System.out.println("1.Ԥ������\t2.ɾ��Ԥ��\t3.�鿴Ԥ��\t4.Ԥ������\t5.����");
        System.out.println("��ѡ��Ҫ���еĲ���:");
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
            System.out.println("��������ȷ��ʽ������1-5");
            System.out.println("\n\n");
            ReserveManage(reserveManage);
        }
    }

    /**
     * XX
     * Ԥ������
     * @param memberManage
     */
    private void MemberManage(DefaultMember_login memberManage) {
        System.out.println("***********��ӭ���л�Ա����*************");

        System.out.println("1.ע���Ա\t2.���һ�Ա\t3.ɾ����Ա\t4.�鿴��Ա\t5.�޸Ļ�Ա����\t6.����");
        System.out.println("��ѡ��Ҫ���еĲ���:");
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
            System.out.println("��������ȷ��ʽ������(1-6)");
            System.out.println("\n\n");
            MemberManage(memberManage);
        }
    }

    /**
     * XX
     * ��Ʒ����
     * @param foodManage
     */
    private void FoodManage(FoodManage_login foodManage) {
        System.out.println("***********��ӭ���в�Ʒ����*************");

        System.out.println("1.�鿴�˵�\t2.����²�\t3.ɾ���ɲ�\t4.����ĳ��\t5.����");
        System.out.println("��ѡ��Ҫ���еĲ���:");
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
                    System.out.println("������Ҫ���ҵĲ���:");
                    String findFoodName = input.next();
                    Food_login findFood = foodManage.findFoodByName(findFoodName);
                    if (findFood != null) {
                        System.out.println("��Ҫ���ҵĲ���:" + findFood.getFoodName() + ",�������ۼ۸���:" + findFood.getPrice());
                    } /*else {
                        System.out.println("�Բ��𣬲˵��в��������ֲ�!");
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
            System.out.println("��������ȷ��ʽ������(1-5)");
            System.out.println("\n\n");
            FoodManage(foodManage);
        }
    }

    /**
     * XX
     * �Բ͹ݵİ�����й���
     * @param roomManage
     */
    public void RoomManage(RoomManage_login roomManage) {
        System.out.println("***********��ӭ���а������*************");

        System.out.println("1.�鿴����\t2.���Ӱ���\t3.ɾ������\t4.����");
        System.out.println("��ѡ��Ҫ���еĲ���:");
        int choose = input.nextInt();
        try {
            switch (choose) {
                case 1:
                    System.out.println("�鿴����״̬");
                    roomManage.SeeRoom_login();
                    System.out.println("\n\n");
                    RoomManage(roomManage);
                    break;
                case 2:
                    System.out.println("���Ӱ���");
                    roomManage.addRoom();
                    System.out.println("\n\n");
                    RoomManage(roomManage);
                    break;
                case 3:
                    System.out.println("ɾ������");
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
            System.out.println("��������ȷ��ʽ������(1-4)");
            RoomManage(roomManage);
        }
    }


}

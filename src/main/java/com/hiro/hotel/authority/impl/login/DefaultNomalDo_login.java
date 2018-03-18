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
 * �û��Ĳ���
 */
public class DefaultNomalDo_login implements NomalDo_login {

    Scanner input = new Scanner(System.in);
    FoodManage_login foodManager = new FoodManage_login();

    //���Ѳ�ѯ
    @Override
    public void ConsuQuery() {
        System.out.println("���������İ����:");
        int roomNumber = input.nextInt();
        if (RoomManage_login.findRoomByNumber(roomNumber) != null) {
            if (!RoomManage_login.findRoomByNumber(roomNumber).isEmpty()) {
                double sum = 0;
                if (RoomManage_login.findRoomByNumber(roomNumber).getFoodList()!= null) {
                    for (int i = 0; i < RoomManage_login.findRoomByNumber(roomNumber).getFoodList().size(); i++) {
                        sum += RoomManage_login.findRoomByNumber(roomNumber).getFoodList().get(i).getPrice();
                    }
                }
                System.out.println("��Ŀǰһ�����ѣ�" + sum + "Ԫ");
            } else {
                System.out.println("�ð����ǿյ�,��ѯʧ��!");
            }
        } else {
            System.out.println("����Ų����ڣ�");
        }

    }

    //�鿴�˵�
    @Override
    public void showBill() {

        System.out.println("���������İ����:");
        int roomNumber = input.nextInt();
        if (RoomManage_login.findRoomByNumber(roomNumber) != null) {
            if (!RoomManage_login.findRoomByNumber(roomNumber).isEmpty()) {
                System.out.println("�����Ϊ" + roomNumber + "�������˵���");
                if (RoomManage_login.findRoomByNumber(roomNumber).getFoodList() != null) {
                    for (int i = 0; i < RoomManage_login.findRoomByNumber(roomNumber).getFoodList().size(); i++) {
                        if (RoomManage_login.findRoomByNumber(roomNumber).getFoodList().get(i).getFoodName() != null) {
                            System.out.println(RoomManage_login.findRoomByNumber(roomNumber).getFoodList().get(i).getFoodName() + "\t"
                                    + RoomManage_login.findRoomByNumber(roomNumber).getFoodList().get(i).getPrice());
                        }
                    }
                }
            } else {
                System.out.println("�ð����ǿյ�!");
            }
        } else {
            System.out.println("����Ų����ڣ�");
        }
    }

    //���
    @Override
    public void oderFood() {
        System.out.println("���������İ����:");
        int roomNumber = input.nextInt();
        if (RoomManage_login.findRoomByNumber(roomNumber) != null) {
            if (!RoomManage_login.findRoomByNumber(roomNumber).isEmpty()) {
                System.out.println("���Ǳ���Ĳ˵�:");
                foodManager.showFood();
                System.out.println("��������Ҫ�����(y/n)��");
                String answer = input.next();
                ArrayList<Food_login> tempFoodList = new ArrayList<>();
                while (answer.equals("y")) {
                    System.out.println("����������ҪԤ���Ĳ���:");
                    String tempFoodNames = input.next();
                    if (foodManager.findFoodByName(tempFoodNames) != null) {
                        tempFoodList.add(foodManager.findFoodByName(tempFoodNames));
                        JroomManage_login.addDish(roomNumber, tempFoodNames);
                        System.out.println("��˳ɹ�!");
                    } else {
                        System.out.println("�Բ�������û�����ֲˣ������ʧ�ܣ�");
                    }
                    System.out.println("�Ƿ�Ҫ�������(y/n):");
                    answer = input.next();
                }
                System.out.println("��˳ɹ��������Եȣ����Ĳ����Ͼ͵�!");
                RoomManage_login.findRoomByNumber(roomNumber).setFoodList(tempFoodList);
            } else {
                System.out.println("�Բ��𣬸ð����ǿյģ��޷����!");
            }
        } else {
            System.out.println("������������!");
        }

    }
    //����
    @Override
    public void checkout() {
        System.out.println("���������İ����:");
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
                System.out.println("���˴�һ����Ҫ֧��:" + sum + "Ԫ");
                RoomManage_login.findRoomByNumber(roomNumber).setEmpty(true);    //���˺��Զ��˻�����
                ExpenseManage_login.expenseManage.add(new Expense_login("�û����н���", sum));
                JroomManage_login.retRoom(roomNumber);
                Jexpense_login.addExpense("�û����н���", sum);
                DefaultMember_login me = new DefaultMember_login();
                me.Integral(HotelTest_login.name, sum);
            } else {
                System.out.println("�ð����ǿյģ�");
            }
        } else {
            System.out.println("�ð��䲻����!");
        }
    }

    //������
    @Override
    public void getRoom() {
        System.out.println("����������Ҫ���İ����:");
        int roomNumber = input.nextInt();
        if (RoomManage_login.findRoomByNumber(roomNumber) != null) {
            if (RoomManage_login.findRoomByNumber(roomNumber).isEmpty()) {
                System.out.println("������ɹ�!");
                RoomManage_login.findRoomByNumber(roomNumber).setEmpty(false);
                JroomManage_login.reserveRoom(roomNumber);
            } else {
                System.out.println("�Բ����޴˰����Ѿ�����,������ʧ��!");
            }
        } else {
            System.out.println("������������!");
        }

    }

    //�鿴����״̬
    @Override
    public void seeRoom() {
        System.out.println("�����\tԤ���۸�\t״̬");
        for (int i = 0; i < RoomManage_login.rooms.size(); i++) {
            System.out.print(RoomManage_login.rooms.get(i).getRoomNumber());
            System.out.print("\t");
            System.out.print(RoomManage_login.rooms.get(i).getRoomprice() + "\t");
            if (RoomManage_login.rooms.get(i).isEmpty()) {
                System.out.println("��");
            } else {
                System.out.println("����");
            }
        }

    }

    //�޸�����
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
        System.out.println("��������Ļ�Ա��");
        number = input.next();
        member = manage.findMemberById_login(number);
        if (member == null) {
            System.out.println("�û�Ա������");
        } else {
            System.out.println("����������");
            password = input.next();
            if (member.getPassword().equals(password)) {
                System.out.println("���������������");
                newpassword = input.next();
                member.setPassword(newpassword);
                JmemberManage_login.rePass(number, newpassword);
                System.out.println("�޸ĳɹ�!");
            } else {
                System.out.println("�������");
            }
        }


    }


}

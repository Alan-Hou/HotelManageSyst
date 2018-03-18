package com.hiro.hotel.biz.login;

import com.hiro.Jdbc.JdishManage_login;
import com.hiro.hotel.entity.login.Food_login;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;
import java.lang.*;

/**
 * @author XX
 * ��Ʒ����
 */
public class FoodManage_login {

    static ArrayList<Food_login> foods = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public static void IniFood() {
        foods = JdishManage_login.getDishes();
    }

    /**
     * ����²�
     */
    public void addFood() {
        boolean isFind = false;
        String tempFoodName = null;
        double tempFoodPrice = 0;
        System.out.println("������Ҫ��ӵ��²˵�����:");
        tempFoodName = input.next();
        if (JdishManage_login.search(tempFoodName) == true) {
            System.out.println("�ò�Ʒ�Ѵ���");
        } else {
            System.out.println("������Ҫ��ӵ��²˵����ۼ۸�:");
            tempFoodPrice = input.nextDouble();
            Food_login newFood = new Food_login(tempFoodName, tempFoodPrice);
            foods.add(newFood);
            JdishManage_login.addDish(tempFoodName, tempFoodPrice);
            System.out.println("�²���ӳɹ�!");
        }
    }

    /**
     * ɾ��ĳ��
     */
    public void delFood() {
        System.out.println("������Ҫɾ���Ĳ���:");
        String delFoodName = input.next();
        Food_login delFood = findFoodByName(delFoodName);
        if (JdishManage_login.search(delFoodName) == false) {
        } else {
                foods.remove(delFood);
                JdishManage_login.delDish(delFoodName);
                System.out.println("ɾ����Ʒ�ɹ�");
        }
    }

    public Food_login findFoodByName(String name) {

        if(JdishManage_login.search(name)==false)
        {
            System.out.println("�ò�Ʒ������");
            return null;
        }
        else{
           return  JdishManage_login.searchRet(name);

        }

    }

    //��ʾ�˵���Ϣ
    public void showFood()
    {
        System.out.println("******************�˵�********************");
        System.out.println("���\t\t��Ʒ����" + "\t\t" + "�۸�");
        int i=0;
        HashMap <Integer, Food_login>foods=null;
        foods= JdishManage_login.show();
        Iterator<Map.Entry<Integer,Food_login>> iter = foods.entrySet().iterator();//������
        while (iter.hasNext())
        {
            Map.Entry<Integer,Food_login> entry = iter.next();
            System.out.println(i + 1 + "\t\t" + entry.getValue().getFoodName() + "\t\t" + entry.getValue().getPrice()+"Ԫ");
            i++;
        }
        i=0;
    }
}

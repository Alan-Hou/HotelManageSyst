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
 * 菜品管理
 */
public class FoodManage_login {

    static ArrayList<Food_login> foods = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public static void IniFood() {
        foods = JdishManage_login.getDishes();
    }

    /**
     * 添加新菜
     */
    public void addFood() {
        boolean isFind = false;
        String tempFoodName = null;
        double tempFoodPrice = 0;
        System.out.println("请输入要添加的新菜的名称:");
        tempFoodName = input.next();
        if (JdishManage_login.search(tempFoodName) == true) {
            System.out.println("该菜品已存在");
        } else {
            System.out.println("请输入要添加的新菜的销售价格:");
            tempFoodPrice = input.nextDouble();
            Food_login newFood = new Food_login(tempFoodName, tempFoodPrice);
            foods.add(newFood);
            JdishManage_login.addDish(tempFoodName, tempFoodPrice);
            System.out.println("新菜添加成功!");
        }
    }

    /**
     * 删除某菜
     */
    public void delFood() {
        System.out.println("请输入要删除的菜名:");
        String delFoodName = input.next();
        Food_login delFood = findFoodByName(delFoodName);
        if (JdishManage_login.search(delFoodName) == false) {
        } else {
                foods.remove(delFood);
                JdishManage_login.delDish(delFoodName);
                System.out.println("删除菜品成功");
        }
    }

    public Food_login findFoodByName(String name) {

        if(JdishManage_login.search(name)==false)
        {
            System.out.println("该菜品不存在");
            return null;
        }
        else{
           return  JdishManage_login.searchRet(name);

        }

    }

    //显示菜单信息
    public void showFood()
    {
        System.out.println("******************菜单********************");
        System.out.println("序号\t\t菜品名称" + "\t\t" + "价格");
        int i=0;
        HashMap <Integer, Food_login>foods=null;
        foods= JdishManage_login.show();
        Iterator<Map.Entry<Integer,Food_login>> iter = foods.entrySet().iterator();//迭代器
        while (iter.hasNext())
        {
            Map.Entry<Integer,Food_login> entry = iter.next();
            System.out.println(i + 1 + "\t\t" + entry.getValue().getFoodName() + "\t\t" + entry.getValue().getPrice()+"元");
            i++;
        }
        i=0;
    }
}

package com.hiro.Jdbc;

import com.hiro.hotel.entity.login.Food_login;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by houkunpeng on 2017/7/14.
 */
public class JdishManage_login {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.93.216.75:2333/hotel?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    static final String USER = "root";
    static final String PASS = "ZZ-y1998";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构造函数
     */
    public JdishManage_login()
    {

    }

    /**
     * 从数据库get到菜品信息
     * @return
     */
    public static ArrayList<Food_login> getDishes() {
        ArrayList<Food_login> dishes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("SELECT * FROM dish");
            rs = stmt.executeQuery();
            while (rs.next()) {
                //判断是否是管理员调用不同的构造函数
                dishes.add(new Food_login(rs.getString("name"), rs.getDouble("price")));
            }
            return dishes;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return null;
    }

    /**
     * 在数据库加菜品
     * @param name
     * @param price
     */
    public static void addDish(String name, double price) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("INSERT  INTO dish(`name`,`price`) VALUE (?,?)");
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    /**
     * 查找菜品
     * @param name
     * @return
     */
    public static boolean search(String name) {
        boolean isFind = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT * FROM dish WHERE NAME = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals(name)) {
                    return true;
                }
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return false;
    }

    /**
     * 删除菜品
     * @param name
     */
    public static void delDish(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("DELETE FROM dish where name=?");
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    /**
     * 通过菜品名查找并返回菜品
     * @param name
     * @return
     */
    public static Food_login searchRet(String name) {
        Food_login food = new Food_login();
        boolean isFind = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT * FROM dish WHERE NAME = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals(name)) {
                    food.setFoodName(rs.getString("name"));
                    food.setPrice(rs.getDouble("price"));
                }
            }
            //STEP 4: Execute a query
            return food;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return null;
    }

    /**
     * 返回菜品的Arraylist
     * @return
     */
    public static HashMap<Integer, Food_login> show() {
        HashMap<Integer, Food_login> foods = new HashMap<>();
        Food_login food = new Food_login();
        boolean isFind = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int i = 0;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT * FROM dish ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                foods.put(i++, new Food_login(rs.getString("name"), rs.getDouble("price")));

            }
            i = 0;
            return foods;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return null;
    }
}

package com.hiro.Jdbc;

import com.hiro.hotel.entity.login.Food_login;
import com.hiro.hotel.entity.login.PrivateRoom_login;
import com.hiro.hotel.entity.login.Reserve_login;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by houkunpeng on 2017/7/15.
 */
public class JreserveManage_login {
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
    public JreserveManage_login() {

    }

    /**
     * 从数据库取到订单数据
     * @return
     */
    public static ArrayList<Reserve_login> getReserve()
    {
        ArrayList<Reserve_login> reserve = new ArrayList<>();
        ArrayList<Food_login> fl = new ArrayList<>();
        String[] temp = new String[50];
        String food;
        food = null;
        for (int i = 0; i < 50; i++)
            temp[i] = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("SELECT * FROM reserve");
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("dish") != null) {
                    food = rs.getString("dish");
                    temp = food.split(" ");
                    for (int i = 0; i < temp.length; i++) {
                        if (temp [i]!= null) {
                            fl.add(JdishManage_login.searchRet(temp[i]));
                        } else {
                            break;
                        }
                    }
                    reserve.add(new Reserve_login(rs.getInt("roomNumber"), rs.getString("member"), new ArrayList<>(fl)));
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i] != null) {
                            temp[i] = null;
                        } else {
                            break;
                        }

                    }
                    fl.clear();

                } else {
                    reserve.add(new Reserve_login(rs.getInt("roomNumber"), rs.getString("member"),  null));

                }
            }
            return reserve;
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
     * 添加订单
     * @param roomnum
     * @param membernum
     * @param dish
     */
    public static void addReserve(int roomnum, String membernum, String dish) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("INSERT  INTO reserve(`roomNumber`,`member`,`dish`) VALUE (?,?,?)");
            stmt.setInt(1, roomnum);
            stmt.setString(2, membernum);
            stmt.setString(3, dish);
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
     * 删除订单
     * @param number
     */
    public static void delReserve(int number) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("DELETE FROM reserve where roomNumber =?");
            stmt.setInt(1, number);
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
}

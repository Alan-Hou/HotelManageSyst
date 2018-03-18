package com.hiro.Jdbc;

import com.hiro.hotel.entity.login.Expense_login;
import com.hiro.hotel.entity.login.Member_login;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by houkunpeng on 2017/7/15.
 */
public class Jexpense_login {

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
     *    构造函数
     */
    public Jexpense_login ()
    {

    }

    /**
     * 添加费用信息
     * @param mark
     * @param money
     */
    public static void addExpense(String mark,double money)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("INSERT  INTO expense(`mark`,`money`) VALUE (?,?)");
            stmt.setString(1,mark);
            stmt.setDouble(2,money);
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
     * get费用信息
     * @return
     */
    public static ArrayList<Expense_login> getExpense()
    {
        ArrayList<Expense_login> expense = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("SELECT * FROM expense");
            rs = stmt.executeQuery();
            while (rs.next()) {
                expense.add(new Expense_login(rs.getString("mark"),rs.getDouble("money")));

            }
            return expense;
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

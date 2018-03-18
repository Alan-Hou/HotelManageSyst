package com.hiro.Jdbc;

import com.hiro.hotel.entity.login.Food_login;
import com.hiro.hotel.entity.login.Member_login;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by houkunpeng on 2017/7/14.
 */
public class JmemberManage_login {
    public JmemberManage_login() {

    }

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
     * 从数据库取到用户和会员信息
     * @return
     */
    public static ArrayList<Member_login> getMembers() {
        ArrayList<Member_login> members = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("SELECT * FROM member_login");
            rs = stmt.executeQuery();
            while (rs.next()) {
                //判断是否是管理员调用不同的构造函数
                if (rs.getString("name") == "admin") {
                    members.add(new Member_login(rs.getString("name"), rs.getString("password")));

                } else {
                    members.add(new Member_login(rs.getString("number"), rs.getString("name"), rs.getInt("integral"), rs.getString("password")));

                }

            }
            return members;
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
     * 添加会员
     * @param number
     * @param name
     * @param password
     */
    public static void addMember(String number,String name,String password)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("INSERT  INTO member_login(`number`,`name`,`integral`,`password`) VALUE (?,?,?,?)");
            stmt.setString(1, number);
            stmt.setString(2, name);
            stmt.setInt(3,0);
            stmt.setString(4,password);
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
     * 删除会员
     * @param id
     */
    public static void delMember(String id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("DELETE FROM member_login where NUMBER =?");
            stmt.setString(1, id);
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
     * 查找会员
     * @param id
     * @return
     */
    public static boolean search(String id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT * FROM member_login");
//            stmt.setString(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("number").equals(id)) {
                    return true;
                }
            }
            //STEP 4: Execute a query

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
     * 查找并返回会员
     * @param id
     * @return
     */
    public static Member_login searchRet(String id)
    {
        Member_login member = new Member_login();
        boolean isFind = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT * FROM member_login WHERE NUMBER = ?");
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("number").equals(id)) {
                    member.setNumber(rs.getString("number"));
                    member.setMemberName(rs.getString("name"));
                    member.setIntegral(rs.getInt("integral"));
                    member.setPassword(rs.getString("password"));
                }
            }
            //STEP 4: Execute a query
            return member;
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
     * 通过会员名查找并返回会员
     * @param name
     * @return
     */
    public static Member_login searchNmaeRet(String name)
    {
        Member_login member = new Member_login();
        boolean isFind = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT * FROM member_login WHERE NAME = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals(name)) {
                    member.setNumber(rs.getString("number"));
                    member.setMemberName(rs.getString("name"));
                    member.setIntegral(rs.getInt("integral"));
                    member.setPassword(rs.getString("password"));
                }
            }
            //STEP 4: Execute a query
            return member;
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
     * 修改会员积分
     * @param id
     * @param inte
     */
    public static void modifyIntegral(String id,int inte)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt=conn.prepareStatement("UPDATE member_login SET integral=? WHERE number=?");
            stmt.setInt(1,inte);
            stmt.setString(2,id);
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
     * 修改密码
     * @param id
     * @param pass
     */
    public static void rePass(String id,String pass)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt=conn.prepareStatement("UPDATE member_login SET password=? WHERE number=?");
            stmt.setString(1,pass);
            stmt.setString(2,id);
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

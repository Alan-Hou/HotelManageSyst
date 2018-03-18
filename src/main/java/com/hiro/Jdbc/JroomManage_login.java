package com.hiro.Jdbc;

import com.hiro.hotel.entity.login.Food_login;
import com.hiro.hotel.entity.login.PrivateRoom_login;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by houkunpeng on 2017/7/15.
 */
public class JroomManage_login {
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
    public JroomManage_login() {

    }

    /**
     * 从数据库获取房间信息
     * @return
     */
    public static ArrayList<PrivateRoom_login> getRoom() {
        ArrayList<PrivateRoom_login> rooms = new ArrayList<>();
        ArrayList<Food_login> foodlist = new ArrayList<>();
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
            stmt = conn.prepareStatement("SELECT * FROM room");
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("foodList") != null) {
                    food = rs.getString("foodList");
                    temp = food.split(" ");
                    for (int i = 0; i < temp.length; i++) {
                        if (temp [i]!= null||temp[i]==" ") {
                            foodlist.add(JdishManage_login.searchRet(temp[i]));
                        }
                    }
                    rooms.add(new PrivateRoom_login(rs.getInt("roomNumber"), rs.getBoolean("isEmpty"), rs.getDouble("roomPrice"), new ArrayList<Food_login>(foodlist)));
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i] != null) {
                            temp[i] = null;
                        } else {
                            break;
                        }

                    }
                    foodlist.clear();
                } else {
                    rooms.add(new PrivateRoom_login(rs.getInt("roomNumber"), rs.getBoolean("isEmpty"), rs.getDouble("roomPrice"), null));

                }
            }
            return rooms;
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
     * 添加房间
     * @param roomnumber
     * @param roomprice
     */
    public static void addRoom(int roomnumber, double roomprice) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("INSERT  INTO room (`roomNumber`,`isEmpty`,`roomPrice`,`foodList`)VALUE (?,?,?,?)");
            stmt.setInt(1, roomnumber);
            stmt.setBoolean(2, true);
            stmt.setDouble(3, roomprice);
            stmt.setString(4, null);
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
     * 删除包间
     * @param number
     */
    public static void delRoom(int number) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt = conn.prepareStatement("DELETE FROM room where roomNumber =?");
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

    /**
     * 查询包间菜品
     * @param id
     * @return
     */
    public static String searchDish(int id) {
        String temp = null;
        boolean isFind = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT * FROM room WHERE roomNumber = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("roomNumber")==id) {
                    temp=rs.getString("foodList");
                }
            }
            //STEP 4: Execute a query
            return temp;
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
     * 预定包间
     * @param number
     */
    public static void reserveRoom(int number)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt=conn.prepareStatement("UPDATE room SET isEmpty=? WHERE roomNumber=?");
            stmt.setBoolean(1,false);
            stmt.setInt(2,number);
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
     *归还包间
     * @param number
     */
    public static void retRoom(int number)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt=conn.prepareStatement("UPDATE room SET isEmpty=?, foodList=? WHERE roomNumber=?");
            stmt.setBoolean(1,true);
            stmt.setString(2,null);
            stmt.setInt(3,number);
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
     * 在包间添加菜品
     * @param number
     * @param dish
     */
    public static void addDish(int number,String dish)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String temp;
        temp=null;
        temp=JroomManage_login.searchDish(number);
        if(temp==null)
        {
            temp=dish;
        }
        else
        {
            temp=temp+" "+dish;
        }
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//获取链接
            stmt=conn.prepareStatement("UPDATE room SET foodList=? WHERE roomNumber=?");
            stmt.setString(1,temp);
            stmt.setInt(2,number);
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

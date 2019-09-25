package com.java.Demo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dblink {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/testsys";
    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) {
        /*List<User> user = select();
        for (User user1:user){
            System.out.println(user1);
        }*/
        /*User user = new User(2,"小明","456");
        Save(user);*/
//        User user = new User(2,"小红","666");
//        Update(user);
        User user = new User(2,"","");
        Delete(user);

    }
    public static List<User> select(){
        ArrayList<User> list = new ArrayList<User>();
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String sql = "select * from usermes";
            PreparedStatement pres = conn.prepareStatement(sql);
            ResultSet resultSet = pres.executeQuery();
            User user  = null;
            while (resultSet.next()){
                user = new User();
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getNString("password");
                user.setId(id);
                user.setName(name);
                user.setPassword(password);
                list.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    static void Delete(User user){
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String sql = "delete  from usermes where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1,user.getId());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void Save(User user){
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String sql = "insert into usermes (id,name,password) values(null,?,?)";
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setObject(1,user.getName());
            pstat.setObject(2,user.getPassword());
            int i = pstat.executeUpdate();
            if (i>0){
                System.out.println("添加成功");
            }else {
                System.out.println("添加失败");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void Update(User user){
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String sql = "update usermes set name = ?,password = ?where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(3,user.getId());
            preparedStatement.setObject(1,user.getName());
            preparedStatement.setObject(2,user.getPassword());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.fpa.softw.movlib.dao;

import com.fpa.softw.movlib.connection.ConnectionFactory;
import com.fpa.softw.movlib.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(){
        this.connection = ConnectionFactory.getConnection();
    }

    public boolean addUser(User user){

        String sql = "INSERT INTO user (name, username, password) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.execute();

            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean removeUser(String username){
        String sql = "DELETE FROM user WHERE username=" + username;

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findUser(String username){
        for(User user: this.listUsers()){
            if (user.getUsername().toLowerCase().equals(username.toLowerCase())){
                return user;
            }
        }

        return null;
    }

    public User findUser(int id){
        for(User user: this.listUsers()){
            if (id == user.getId()){
                return user;
            }
        }

        return null;
    }

    public List<User> listUsers(){
        String sql = "SELECT * FROM user";

        List<User> userList = new ArrayList<>();

        try {
            ResultSet rs = this.connection.prepareStatement(sql).executeQuery();


            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setUsername(rs.getString(3));
                user.setPassword(rs.getString(4));

                userList.add(user);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

}

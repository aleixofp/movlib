package com.fpa.softw.movlib.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movlib?useSSL=false", "root", "memroot");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERRO: " + e.getMessage() + " | " + e.getClass());
        }

        return connection;
    }


}

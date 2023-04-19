package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection connection = null;  // initial stage it is going to be null

    /* this method is going to setup the connection, with database*/
    public static Connection getConnection(){
        // if connection object is not null. it means we already have a connection object , just return the connection object
        if(connection != null){
            return  connection;
        }

        /* and if it is not null,, then we need three values
         * 1. user name of my sql
         * 2. user password
         * 3. database name*/
        String user = "root";
        String password = "#@incorrect";
        String db = "searchengineapp";

        // this getConnection method is not same as above getConnection method, this is just overloaded method of below getConnection method
        return  getConnection(user,password,db); // we need to return the connection
    }

    private static Connection getConnection(String user, String password, String db){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // driver class is going to get call
            connection = DriverManager.getConnection("jdbc:mysql://localhost/searchengineapp","root","#@incorrect");
        } catch (SQLException | ClassNotFoundException sqlException){
            sqlException.printStackTrace();
        }

        return connection;
    }

    public static void main(String[] args) {
        getConnection();
        System.out.println("Connected");
    }
}


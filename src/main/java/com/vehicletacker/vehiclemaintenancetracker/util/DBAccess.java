package com.vehicletacker.vehiclemaintenancetracker.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "iamJefe1";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE = "vehiclemaintenanceDB";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;

    private static Connection connection;
    private static DBAccess dbAccess;

    public DBAccess(){
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
     public static DBAccess getInstance() {
         if (dbAccess == null) {
             dbAccess = new DBAccess();
         }
         return dbAccess;
     }

    public static Connection getConnection() {
        try{
            if (connection == null || connection.isClosed()){

                connection = DriverManager.getConnection(
                        URL,
                        USERNAME,
                        PASSWORD
                );
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (Exception e) {
            return false;

        }

         }
    public Connection getconnection() {
        return connection;
    }
     }




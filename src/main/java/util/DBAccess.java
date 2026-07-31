package util;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBAccess {
    private final String USERNAME = "root";
    private final String PASSWORD = "iamJefe1";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DATABASE = "vehiclemaintenanceDB";
    private final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;

    private Connection connection;
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




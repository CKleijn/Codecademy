package database;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class ConnectToDatabase {
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
            return conn;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}

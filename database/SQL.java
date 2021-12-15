package database;

import java.sql.*;

public class SQL {

    public void connectDB(String query) {
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = query;
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                if(SQL.contains("Student")) {
                    if(!SQL.contains("INSERT INTO") || !SQL.contains("UPDATE") || !SQL.contains("DELETE")) {
                        StudentSQL studentSQL = new StudentSQL();
                        studentSQL.getStudentSQL(rs);
                    } else {
                        break;
                    }
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }
}

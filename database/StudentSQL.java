package database;

import java.sql.*;

public class StudentSQL {
    public void getStudentSQL(String query) {
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
                String studentEmail = rs.getString("StudentEmail");
                String studentName = rs.getString("StudentName");
                Date studentBirthdate = rs.getDate("StudentBirthdate");
                String studentGender = rs.getString("StudentGender");
                String studentAddress = rs.getString("StudentAddress");
                String studentResidence = rs.getString("StudentResidence");
                String studentCountry = rs.getString("StudentCountry");

                StringBuilder str = new StringBuilder();

                str.append("Email: " + studentEmail);
                str.append("\n");
                str.append("Name: " + studentName);
                str.append("\n");
                str.append("Birthdate: " + studentBirthdate);
                str.append("\n");
                str.append("Gender: " + studentGender);
                str.append("\n");
                str.append("Address: " + studentAddress);
                str.append("\n");
                str.append("Residence: " + studentResidence);
                str.append("\n");
                str.append("Country: " + studentCountry);
                str.append("\n");

                System.out.println(str.toString());
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception e) {
                }
        }
    }
}

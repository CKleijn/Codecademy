package database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentSQL {
    public void getStudentSQL(ResultSet rs) {
        try {
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
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

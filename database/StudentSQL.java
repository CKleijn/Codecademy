package database;

import domain.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentSQL {
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

    public ObservableList<Student> getStudentList() {
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM Student";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Student student;
            while(rs.next()) {
                student = new Student(rs.getString("StudentEmail"), rs.getString("StudentName"), rs.getString("StudentBirthdate"), rs.getString("StudentGender"), rs.getString("StudentAddress"), rs.getString("StudentResidence"), rs.getString("StudentCountry"));
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void createStudent(Student student) {
        Connection conn = getConnection();
        String query = "INSERT INTO Student VALUES ('" + student.getEmail() + "', '" + student.getName() + "', '" + student.getDateOfBirth() + "', '" + student.getGender() + "', '" + student.getAddress() + "', '" + student.getLivingPlace()  + "', '" + student.getCountry() + "')";
        Statement st;
        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Student created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


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
                student = new Student(rs.getString("StudentEmail"), rs.getString("StudentName"), rs.getInt("StudentBirthDay"), rs.getInt("StudentBirthMonth"), rs.getInt("StudentBirthYear"), rs.getString("StudentGender"), rs.getString("StudentStreet"), rs.getString("StudentHouseNumber"), rs.getString("StudentHouseNumberAddition"), rs.getString("StudentPostalCode"), rs.getString("StudentResidence"), rs.getString("StudentCountry"));
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void createStudent(Student student) {
        Connection conn = getConnection();
        String query = "INSERT INTO Student VALUES ('" + student.getEmail() + "', '" + student.getName() + "', '" + student.getBirthDay() + "', '" + student.getBirthMonth() + "', '" + student.getBirthYear() + "', '" + student.getGender() + "', '" + student.getStreet() + "', '" + student.getHouseNumber() + "', '" + student.getHouseNumberAddition() + "', '" + student.getPostalCode() + "', '" + student.getResidence()  + "', '" + student.getCountry() + "')";
        Statement st;
        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Student created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        Connection conn = getConnection();
        String query = "UPDATE Student SET StudentEmail = '" + student.getEmail() + "', StudentName = '" + student.getName() + "', StudentBirthDay = '" + student.getBirthDay() + "', StudentBirthMonth = '" + student.getBirthMonth() + "', StudentBirthYear = '" + student.getBirthYear() + "', StudentGender = '" + student.getGender() + "', StudentStreet = '" + student.getStreet() + "', StudentHouseNumber = '" + student.getHouseNumber() + "', StudentHouseNumberAddition = '" + student.getHouseNumberAddition() + "', StudentPostalCode = '" + student.getPostalCode() + "', StudentResidence = '" + student.getResidence()  + "', StudentCountry = '" + student.getCountry() + "' WHERE StudentEmail = '" + student.getEmail() + "'";
        Statement st;
        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Student updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(Student student) {
        Connection conn = getConnection();
        String query = "DELETE FROM Student WHERE StudentEmail = '" + student.getEmail() + "'";
        Statement st;
        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Student deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


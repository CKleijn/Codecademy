package database;

import domain.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

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

        if(!checkEmail(student.getEmail()) && checkDate(student.getBirthDay(), student.getBirthMonth(), student.getBirthYear()) && checkPostalCode(student.getPostalCode()) 
        && checkGender(student.getGender()) && !student.getName().isEmpty() && !student.getStreet().isEmpty() && !student.getResidence().isEmpty() && !student.getCountry().isEmpty()){
            try {
                st = conn.createStatement();
                st.executeQuery(query);
                System.out.println("Student created!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStudent(Student student) {
        Connection conn = getConnection();
        String query = "UPDATE Student SET StudentEmail = '" + student.getEmail() + "', StudentName = '" + student.getName() + "', StudentBirthDay = '" + student.getBirthDay() + "', StudentBirthMonth = '" + student.getBirthMonth() + "', StudentBirthYear = '" + student.getBirthYear() + "', StudentGender = '" + student.getGender() + "', StudentStreet = '" + student.getStreet() + "', StudentHouseNumber = '" + student.getHouseNumber() + "', StudentHouseNumberAddition = '" + student.getHouseNumberAddition() + "', StudentPostalCode = '" + student.getPostalCode() + "', StudentResidence = '" + student.getResidence()  + "', StudentCountry = '" + student.getCountry() + "' WHERE StudentEmail = '" + student.getEmail() + "'";
        Statement st;

        if(checkDate(student.getBirthDay(), student.getBirthMonth(), student.getBirthYear()) && checkPostalCode(student.getPostalCode()) && checkGender(student.getGender())
        && !student.getName().isEmpty() && !student.getStreet().isEmpty() && !student.getResidence().isEmpty() && !student.getCountry().isEmpty()){
            try {
                st = conn.createStatement();
                st.executeQuery(query);
                System.out.println("Student updated!");
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    
    public boolean checkEmail(String email){
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if(email.matches(regex)){
            System.out.println("Your email is correct");
            return false;
        }
        System.out.println("Your email format is incorrect");
        return true;
    }

    public boolean checkDate(int day, int month, int year){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if((day <= 31 && day > 0) && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && currentYear >= year && year > currentYear-120){
            System.out.println("The given date is correct");
            return true;
        } else if ((day <= 30 && day > 0) && (month == 4 || month == 6 || month == 9 || month == 11)  && currentYear >= year && year > currentYear-120) {
            System.out.println("The given date is correct");
            return true;
        } else if ((day <= 28 && day > 0) && month == 2 && currentYear >= year && year > currentYear-120){
            System.out.println("The given date is correct");
            return true;
        }

        System.out.println("The given date in incorrect");
        return false;
       
    }

    public boolean checkPostalCode(String pc){
        String regex = "[1-9][0-9]{3}[ ][A-Z]{2}";

        if(pc.matches(regex)){
            System.out.println("The postal code is correct");
            return true;
        }

        System.out.println("The postal code is incorrect");
        return false;

    }

    public boolean checkGender(String gender){
        String regex = "[MF]";

        if(gender.matches(regex)){
            System.out.println("The gender is correct");
            return true;
        }

        System.out.println("The gender is incorrect");
        return false;
    }
}

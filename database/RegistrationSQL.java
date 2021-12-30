package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import domain.Registration;
import domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RegistrationSQL extends ConnectToDatabase {
    public ObservableList<Registration> getStudentRegistrationList(Student current_student) {
        ObservableList<Registration> studentRegistrationList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM Registration WHERE StudentEmail = '" + current_student.getEmail() + "'";
        Statement st;
        ResultSet rs;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Registration registration;
            while(rs.next()) {
                registration = new Registration(rs.getDate("RegistrationDate"), rs.getString("StudentEmail"), rs.getString("CourseName"), rs.getInt("CertificateID"));
                studentRegistrationList.add(registration);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentRegistrationList;
    }

    public void createRegistration(Registration registration) {
        Connection conn = getConnection();
        String query = "INSERT INTO Registration VALUES ('" + registration.getRegistrationDate() + "', '" + registration.getStudentEmail() + "', '" + registration.getCourseName() + "', '" + registration.getCertificateID() + "')";
        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Registration created!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateRegistration(Registration current_registration, Registration new_registration) {
        Connection conn = getConnection();
        String query = "UPDATE Registration SET RegistrationDate = '" + new_registration.getRegistrationDate() + "', StudentEmail = '" + new_registration.getStudentEmail() + "', CourseName = '" + new_registration.getCourseName() + "', CertificateID = '" + new_registration.getCertificateID() + "' WHERE RegistrationDate = '" + current_registration.getRegistrationDate() + "' AND StudentEmail = '" + current_registration.getStudentEmail() + "' AND CourseName = '" + current_registration.getCourseName() + "'";
        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Registration updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteRegistration(Registration registration) {
        Connection conn = getConnection();
        String query = "DELETE FROM Registration WHERE RegistrationDate = '" + registration.getRegistrationDate() + "' AND StudentEmail = '" + registration.getStudentEmail() + "' AND CourseName = '" + registration.getCourseName() + "'";
        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Registration deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

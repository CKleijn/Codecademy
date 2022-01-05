package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.Certificate;
import domain.Course;
import domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CertificateSQL extends ConnectToDatabase {
    public ObservableList<Certificate> getCertificateListFromStudent(Student student) {
        ObservableList<Certificate> certificateList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM Certificate INNER JOIN Registration ON Certificate.CertificateID = Registration.CertificateID WHERE StudentEmail = '" + student.getEmail() + "'";
        Statement st;
        ResultSet rs;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Certificate certificate;
            while(rs.next()) {
                certificate = new Certificate(rs.getInt("CertificateID"), rs.getInt("CertificateGrade"), rs.getInt("ExternalPersonID"));
                certificateList.add(certificate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certificateList;
    }

    public Certificate[] getCertificateFromStudentForSpecificCourse(Course course, Student student) {
        Connection conn = getConnection();
        ArrayList<Certificate> certificates = new ArrayList<>();
        String query = "SELECT * FROM Certificate INNER JOIN Registration ON Certificate.CertificateID = Registration.CertificateID WHERE Registration.CourseName = '" + course.getName() + "' AND Registration.StudentEmail = '" + student.getEmail() + "'";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Certificate certificate;
            while(rs.next()){
                certificate = new Certificate(rs.getInt("CertificateID"), rs.getInt("CertificateGrade"), rs.getInt("ExternalPersonID"));
                certificates.add(certificate);
            }
            System.out.println("got the certificates!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Certificate[] strCertificates = new Certificate[certificates.size()];
        for(int i = 0; i < certificates.size(); i++){
            strCertificates[i] = certificates.get(i);
        }

        return strCertificates;

    }

    public void createCertificate(Certificate certificate) {
        Connection conn = getConnection();
        String query = "INSERT INTO Certificate VALUES ('" + certificate.getCertificateGrade() + "', '" + certificate.getExternalPersonID() + "')";
        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Certificate created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCertificate(Certificate certificate) {
        Connection conn = getConnection();
        String query = "UPDATE Certificate SET CertificateGrade = '" + certificate.getCertificateGrade() + "', ExternalPersonID = '" + certificate.getExternalPersonID() + "' WHERE CertificateID = '" + certificate.getCertificateID() + "'";
        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Certificate updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCertificate(Certificate certificate) {
        Connection conn = getConnection();
        String query = "DELETE FROM Certificate WHERE CertificateID = '" + certificate.getCertificateID() + "'";
        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Certificate deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.Certificate;
import domain.Course;
import domain.Student;

public class CertificateSQL extends ConnectToDatabase {
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
}

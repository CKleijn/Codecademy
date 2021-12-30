package domain;

import java.util.Date;

public class Registration {
    private Date registrationDate;
    private String studentEmail;
    private String courseName;
    private int certificateID;

    public Registration(Date registrationDate, String studentEmail, String courseName, int certificateID){
        this.registrationDate = registrationDate;
        this.studentEmail = studentEmail;
        this.courseName = courseName;
        this.certificateID = certificateID;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCertificateID() {
        return certificateID;
    }
}

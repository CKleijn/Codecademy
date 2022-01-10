package domain;

public class Certificate {
    private int certificateID;
    private int certificateGrade;
    private int externalPersonID;
    private String studentEmail;
    private String courseName;

    public Certificate(int certificateID, int certificateGrade, int externalPersonID, String studentEmail, String courseName) {
        this.certificateID = certificateID;
        this.certificateGrade = certificateGrade;
        this.externalPersonID = externalPersonID;
        this.studentEmail = studentEmail;
        this.courseName = courseName;
    }

    public Certificate(int certificateGrade, int externalPersonID, String studentEmail, String courseName) {
        this.certificateGrade = certificateGrade;
        this.externalPersonID = externalPersonID;
        this.studentEmail = studentEmail;
        this.courseName = courseName;
    }

    public int getCertificateID() {
        return certificateID;
    }

    public int getCertificateGrade() {
        return certificateGrade;
    }

    public int getExternalPersonID() {
        return externalPersonID;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getCourseName() {
        return courseName;
    }
}

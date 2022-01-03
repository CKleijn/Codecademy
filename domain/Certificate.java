package domain;

public class Certificate {
    private int certificateID;
    private int certificateGrade;
    private int externalPersonID;

    public Certificate(int certificateID, int certificateGrade, int externalPersonID) {
        this.certificateID = certificateID;
        this.certificateGrade = certificateGrade;
        this.externalPersonID = externalPersonID;
    }

    public Certificate(int certificateGrade, int externalPersonID) {
        this.certificateGrade = certificateGrade;
        this.externalPersonID = externalPersonID;
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
}

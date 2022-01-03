package domain;

import java.sql.Date;

public class Module extends Item {
    
    private int serialNumber;
    private String version;

    public Module(int itemId, String title, String description, Date publicationDate, int externalPerson, String status, int serialNumber, String version) {
        super(itemId, title, description, publicationDate, externalPerson, status);
        this.serialNumber = serialNumber;
        this.version = version;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getVersion() {
        return version;
    }
}

package domain;

import java.time.LocalDate;

public class Module extends Item {
    
    private int serialNumber;
    private int version;

    public Module(int itemId, String title, String description, LocalDate publicationDate, ExternalPerson externalPerson, Status status, int serialNumber, int version) {
        super(itemId, title, description, publicationDate, externalPerson, status);
        this.serialNumber = serialNumber;
        this.version = version;
    }

    public int getserialNumber() {
        return serialNumber;
    }

    public int getVersion() {
        return version;
    }
}

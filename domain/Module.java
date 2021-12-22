package domain;

import java.time.LocalDate;

public class Module extends Item {
    
    private int indexNumber;
    private int version;

    public Module(int itemId, String title, String description, LocalDate publicationDate, ExternalPerson externalPerson, Status status, int indexNumber, int version) {
        super(itemId, title, description, publicationDate, externalPerson, status);
        this.indexNumber = indexNumber;
        this.version = version;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public int getVersion() {
        return version;
    }
}

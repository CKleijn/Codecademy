package domain;

import java.time.LocalDate;

public class Module extends Item {
    
    private int indexNumber;
    private int version;

    public Module(int itemId, String title, String description, LocalDate publicationDate, Status status, int indexNumber, int version) {
        super(itemId, title, description, publicationDate, status);
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

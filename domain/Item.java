package domain;

import java.time.LocalDate;

public class Item {

    private int itemId;
    private String title;
    private String description;
    private LocalDate publicationDate;
    private ExternalPerson externalPerson;
    private Status status;
    private int viewCount;

    public Item(int itemId, String title, String description, LocalDate publicationDate, ExternalPerson externalPerson, Status status) {
        this.itemId = itemId;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.externalPerson = externalPerson;
        this.status = status;
        this.viewCount = 0;
    }

    public int getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public ExternalPerson getExternalPerson() {
        return externalPerson;
    }

    public Status getStatus() {
        return status;
    }

    public int getViewCount() {
        return viewCount;
    }
}

package domain;

import java.time.LocalDate;

public abstract class Item {

    private int itemId;
    private String title;
    private String description;
    private LocalDate publicationDate;
    private Status status;
    private int viewCount;

    public Item(int itemId, String title, String description, LocalDate publicationDate, Status status) {
        this.itemId = itemId;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
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

    public Status getStatus() {
        return status;
    }

    public int getViewCount() {
        return viewCount;
    }
}

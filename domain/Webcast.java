package domain;

import java.time.LocalDate;

public class Webcast extends Item {

    private String url;

    public Webcast(int itemId, String title, String description, LocalDate publicationDate, Status status, String url) {
        super(itemId, title, description, publicationDate, status);
        this.url = url;
    }
    
    public String getUrl() {
        return url;
    }
}

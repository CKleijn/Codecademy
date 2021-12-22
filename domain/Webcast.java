package domain;

import java.time.LocalDate;

public class Webcast extends Item {

    private int duration;
    private String url;

    public Webcast(int itemId, String title, String description, LocalDate publicationDate, ExternalPerson externalPerson, Status status, int duration, String url) {
        super(itemId, title, description, publicationDate, externalPerson, status);
        this.duration = duration;
        this.url = url;
    }
    
    public int getDuration() {
        return duration;
    }

    public String getUrl() {
        return url;
    }
}

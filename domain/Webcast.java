package domain;

import java.sql.Date;

public class Webcast extends Item {

    private int duration;
    private String url;

    public Webcast(int itemId, String title, String description, Date publicationDate, int externalPerson, String status, int duration, String url) {
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

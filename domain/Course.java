package domain;

public class Course {
    private String name;
    private String topic;
    private String introduction;
    private String level;
    private String relCourse;

    public Course(String name, String topic, String introduction, String level, String relCourse){
        this.name = name;
        this.topic = topic;
        this.introduction = introduction;
        this.level = level;
        this.relCourse = relCourse;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getLevel() {
        return level;
    }

    public String getRelCourse() {
        return relCourse;
    }
}

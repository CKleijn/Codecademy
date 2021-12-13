package Domain;

import java.time.LocalDate;

public class Student {

    private String email;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String livingPlace;
    private String country;

    public Student(String email, String name, LocalDate dateOfBirth, String gender, String livingPlace, String country){
        this.email = email;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.livingPlace = livingPlace;
        this.country = country;
    }

    public String getStudents(){
        return null;
    }

    public String getStudent(Student student){
        return null;
    }

    public void addStudent(Student student){

    }

    public void updateStudent(Student student){

    }

    public void deleteStudent(Student student){

    }
    
}
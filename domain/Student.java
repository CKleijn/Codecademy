package domain;

import java.time.LocalDate;

import database.SQL;

public class Student {

    private String email;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String livingPlace;
    private String country;

    public Student(String email, String name, LocalDate dateOfBirth, String gender, String address, String livingPlace, String country){
        this.email = email;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.livingPlace = livingPlace;
        this.country = country;
    }

    public Student(){
        
    }

    public void getStudents(){
        SQL sql = new SQL();
        sql.connectDB("SELECT * FROM Student");
    }

    public void getStudent(Student student){
        SQL sql = new SQL();
        sql.connectDB("SELECT * FROM Student WHERE StudentEmail = '" + student.email + "'");
    }

    public void addStudent(Student student){
        SQL sql = new SQL();
        sql.connectDB("INSERT INTO Student VALUES (" + "'" + student.email + "'" + ", " + "'" + student.name + "'" + ", " + "'" + student.dateOfBirth + "'" + ", " + "'" + student.gender + "'" + ", " + "'" + student.address + "'" + ", " + "'" + student.livingPlace  + "'" + ", " + "'" + student.country + "'" + ")");
    }

    public void updateStudent(Student student){
        SQL sql = new SQL();
        sql.connectDB("UPDATE Student SET StudentEmail = '" + student.email + "'" + ", " + "StudentName = '" + student.name + "'" + ", " + "StudentBirthdate = '" + student.dateOfBirth + "'" + ", " + "StudentGender = '" + student.gender + "'" + ", " + "StudentAddress = '" + student.address + "'" + ", " + "StudentResidence = '" + student.livingPlace  + "'" + ", " + "StudentCountry = '" + student.country + "'" + "WHERE StudentEmail = '" + student.email + "'");
    }

    public void deleteStudent(Student student){
        SQL sql = new SQL();
        sql.connectDB("DELETE FROM Student WHERE StudentEmail = '" + student.email + "'");
    }
    
}
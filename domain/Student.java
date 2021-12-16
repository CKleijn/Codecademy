package domain;

import database.StudentSQL;

public class Student {

    private String email;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String livingPlace;
    private String country;

    public Student(String email, String name, String dateOfBirth, String gender, String address, String livingPlace, String country){
        this.email = email;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.livingPlace = livingPlace;
        this.country = country;
    }

    public void getStudents(){
        StudentSQL sql = new StudentSQL();
        sql.getStudentSQL("SELECT * FROM Student");
    }

    public void getStudent(Student student){
        StudentSQL sql = new StudentSQL();
        sql.getStudentSQL("SELECT * FROM Student WHERE StudentEmail = '" + student.email + "'");
    }

    public void addStudent(Student student){
        StudentSQL sql = new StudentSQL();
        sql.getStudentSQL("INSERT INTO Student VALUES (" + "'" + student.email + "'" + ", " + "'" + student.name + "'" + ", " + "'" + student.dateOfBirth + "'" + ", " + "'" + student.gender + "'" + ", " + "'" + student.address + "'" + ", " + "'" + student.livingPlace  + "'" + ", " + "'" + student.country + "'" + ")");
    }

    public void updateStudent(Student student){
        StudentSQL sql = new StudentSQL();
        sql.getStudentSQL("UPDATE Student SET StudentEmail = '" + student.email + "'" + ", " + "StudentName = '" + student.name + "'" + ", " + "StudentBirthdate = '" + student.dateOfBirth + "'" + ", " + "StudentGender = '" + student.gender + "'" + ", " + "StudentAddress = '" + student.address + "'" + ", " + "StudentResidence = '" + student.livingPlace  + "'" + ", " + "StudentCountry = '" + student.country + "'" + "WHERE StudentEmail = '" + student.email + "'");
    }

    public void deleteStudent(Student student){
        StudentSQL sql = new StudentSQL();
        sql.getStudentSQL("DELETE FROM Student WHERE StudentEmail = '" + student.email + "'");
    }
    
}
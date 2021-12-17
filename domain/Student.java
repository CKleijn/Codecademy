package domain;

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

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getLivingPlace() {
        return livingPlace;
    }

    public String getCountry() {
        return country;
    }   
   
}
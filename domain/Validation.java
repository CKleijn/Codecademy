package domain;

import java.util.Calendar;

public abstract class Validation {
    public boolean checkEmail(String email){
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if(email.matches(regex)){
            System.out.println("Your email is correct");
            return false;
        }
        System.out.println("Your email format is incorrect");
        return true;
    }

    public boolean checkDate(int day, int month, int year){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if((day <= 31 && day > 0) && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && currentYear >= year && year > currentYear-120){
            System.out.println("The given date is correct");
            return true;
        } else if ((day <= 30 && day > 0) && (month == 4 || month == 6 || month == 9 || month == 11)  && currentYear >= year && year > currentYear-120) {
            System.out.println("The given date is correct");
            return true;
        } else if ((day <= 28 && day > 0) && month == 2 && currentYear >= year && year > currentYear-120){
            System.out.println("The given date is correct");
            return true;
        }

        System.out.println("The given date in incorrect");
        return false;
       
    }

    public boolean checkPostalCode(String pc){
        String regex = "[1-9][0-9]{3}[ ][A-Z]{2}";

        if(pc.matches(regex)){
            System.out.println("The postal code is correct");
            return true;
        }

        System.out.println("The postal code is incorrect");
        return false;

    }

    public boolean checkGender(String gender){
        String regex = "[MF]";

        if(gender.matches(regex)){
            System.out.println("The gender is correct");
            return true;
        }

        System.out.println("The gender is incorrect");
        return false;
    }
}

package tests;

public class PostalCode {

    /**
     * @desc Formats the input postal code to a uniform output in the form
     * nnnn<space>MM, where nnnn is numeric and > 999 and MM are 2 capital letters.
     * Spaces before and after the input string are trimmed.
     * 
     * @subcontract null postalCode {
     *   @requires postalCode == null;
     *   @signals (NullPointerException) postalCode == null;
     * }
     * 
     * @subcontract valid postalCode {
     *   @requires Integer.valueOf(postalCode.trim().substring(0, 4)) > 999 &&
     *             Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
     *             postalCode.trim().substring(4).trim().length == 2 &&
     *             'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z' &&
     *             'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z';
     *   @ensures \result = postalCode.trim().substring(0, 4) + " " +
     *                  postalCode.trim().substring(4).trim().toUpperCase()
     * }
     *  
     * @subcontract invalid postalCode {
     *   @requires no other valid precondition;
     *   @signals (IllegalArgumentException);
     * }
     * 
     */
    public static boolean formatPostalCode(String postalCode) {

        if(postalCode == null){
            throw new NullPointerException();
        }
        
        //the postal code need to cantain four digits one space and two letters so the total string length must be 7 to be valid 
        if(postalCode.trim().length() != 7){
            throw new IllegalArgumentException();
        }

        String parts[] = postalCode.split(" ");
        int numberOfPostalCode = Integer.valueOf(parts[0]);

        String[] lettersOfPostalCode = parts[1].split("");
        String firstLetterOfPostalCode = lettersOfPostalCode[0];
        String secondLetterOfPostalCode = lettersOfPostalCode[1];

        if(numberOfPostalCode > 999 && numberOfPostalCode <= 9999 && firstLetterOfPostalCode.matches("[A-Z]") && secondLetterOfPostalCode.matches("[A-Z]")){
            return true;
        }
        throw new IllegalArgumentException();
    }
}
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.Validation;

public class MailToolsTest {
    /**
    * 
    * @subcontract no mailbox part {
    *   @requires the email address misses the part before the at sign
    *   @ensures \result = false;
    * }
    */
    
    @Test
    public void MailAddressMissesFirstPart(){
        // Arrage
        String email = "@gmail.com";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(false, result);
    }

    /**
    * @subcontract subdomain-tld delimiter {
    *   @requires the email address misses the at sign
    *   @ensures \result = false;
    * }
    */

    @Test
    public void MailAddresDoesnotContainAtSing(){
        // Arrage
        String email = "testgmail.com";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(false, result);
    }

    /**
    * @subcontract no subdomain part {
    *   @requires the emailaddress misses the part after the at sign
    *   @ensures \result = false;
    * }
    */

    @Test
    public void MailAddressDoesNotContainAnyThingBeforeTheDot(){
        // Arrage
        String email = "test@.com";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(false, result);
    }

    /**
    * @subcontract no tld part {
    *   @requires the emailaddress misses the part after the dot
    *   @ensures \result = false;
    * }
    */

    @Test
    public void MailAddressDoesNotContainAnyThingAfterTheDot(){
        // Arrage
        String email = "test@gmail.";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(false, result);
    }

    /**
    * @subcontract valid email {
    *   @requires no other precondition
    *   @ensures \result = true;
    * }
    */

    @Test
    public void ValidEmail(){
        // Arrage
        String email = "test@gmail.com";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(true, result);
    }

    /**
    * @subcontract invalid email contains at signs at the end{
    *   @requires multiple at signs at the end of the email
    *   @ensures \result = false;
    * }
    */

    @Test
    public void MailAddressContainsMultipleAtSignsAtTheEnd(){
        // Arrage
        String email = "test@gmail.com@@@";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(false, result);
    }

    /**
    * @subcontract invalid email contains at two at signs{
    *   @requires emailadress contains two at signs 
    *   @ensures \result = false;
    * }
    */

    @Test
    public void MailAddressContainsTwoAtSigns(){
        // Arrage
        String email = "test@@@gmail.com";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(false, result);
    }    

    /**
    * @subcontract valid email with only three letters{
    *   @requires one letter before the at sign one letter after the at sign and one letter after the dot
    *   @ensures \result = true;
    * }
    */

    @Test
    public void MailAddressContainsThreeLetters(){
        // Arrage
        String email = "t@g.c";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(true, result);
    }
    
    /**
    * @subcontract valid email with only capital letters{
    *   @requires The emailAddress only contains capital letters
    *   @ensures \result = true;
    * }
    */

    @Test
    public void MailAddressContainsOnlyCapitalLetters(){
        // Arrage
        String email = "TEST@GMAIL.COM";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(true, result);
    }

    /**
    * @subcontract invalid email contains digit{
    *   @requires The emailAddress contains digits
    *   @ensures \result = false;
    * }
    */

    @Test
    public void MailAddressContainsDigits(){
        // Arrage
        String email = "test3@gmail.com";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(false, result);
    }
    
    /**
    * @subcontract invalid email contains multiple dots{
    *   @requires The emailAddress containss multiple dots one before the at sign and two after the at sign
    *   @ensures \result = false;
    * }
    */

    @Test
    public void MailAddressContainsThreeDots(){
        // Arrage
        String email = "test.test@gmail.com.com";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(false, result);
    }
    
    /**
    * @subcontract invalid email contains two dots{
    *   @requires The emailAddress containss one before the at sign and one after the at sign
    *   @ensures \result = false;
    * }
    */

    @Test
    public void MailAddressContainsTwoDots(){
        // Arrage
        String email = "test.test@gmail.com";
        // Act
        boolean result = Validation.checkEmail(email);
        // Assert
        assertEquals(false, result);
    }
        

}

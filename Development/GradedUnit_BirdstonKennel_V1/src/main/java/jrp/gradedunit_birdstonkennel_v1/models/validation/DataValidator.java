package jrp.gradedunit_birdstonkennel_v1.models.validation;

import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.DogGender;
import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.Size;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * This class handles the validation of inputted data
 */
@Service
public class DataValidator {

    /**
     * Checks that the data of birt entered is not in the future
     *
     * @param dob The date of birth entered
     * @return Whether the data is valid or not
     */
    public boolean dob(LocalDate dob) {
        return dob.isBefore(LocalDate.now().minusYears(16));
    }

    /**
     * This method checks if a date is in the past
     *
     * @param time The time being checked
     * @return Whether the date is in the past or not
     */
    public boolean dateBeforeNow(LocalDate time){
        return time.isBefore(LocalDate.now());
    }

    /**
     * Checks that the data entered is a valid email address
     *
     * @param email The email entered
     * @return Whether the data is valid or not
     */
    public boolean email(String email) {
        final String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(emailPattern).matcher(email).matches();
    }

    /**
     * Checks if the password matches the confirm password
     *
     * @param pass         The password entered
     * @param confirm_pass The confirm password entered
     * @return Whether the data is valid or not
     */
    public boolean passwordMatch(String pass, String confirm_pass) {
        return pass.equals(confirm_pass);
    }

    /**
     * Checks that the password is the correct length more than 8 less than 25
     *
     * @param pass The password being checked
     * @return Whether the data is valid or not
     */
    public boolean passwordCorrectLength(String pass) {
        return (pass.length() >= 8 && pass.length() <= 25);
    }

    /**
     * Checks that the password has at least 1 capital letter
     *
     * @param pass The password being checked
     * @return Whether the data is valid or not
     */
    public boolean passwordHasCaps(String pass) {
        for (char c : pass.toCharArray())
            if (Character.isUpperCase(c))
                return true;
        return false;
    }

    /**
     * Checks the password has at least one lower case letter
     *
     * @param pass The password being checked
     * @return Whether the data is valid or not
     */
    public boolean passwordHasLower(String pass) {
        for (char c : pass.toCharArray())
            if (Character.isLowerCase(c))
                return true;
        return false;
    }

    /**
     * Checks the password has at least one digit
     *
     * @param pass The password being checked
     * @return Whether the data is valid or not
     */
    public boolean passwordHasDigit(String pass) {
        for (char c : pass.toCharArray())
            if (Character.isDigit(c))
                return true;
        return false;
    }

    /**
     * Checks that the password has at least one special character
     *
     * @param pass The password being checked
     * @return Whether the data is valid or not
     */
    public boolean passwordHasSpecial(String pass) {
        for (char c : pass.toCharArray())
            if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c))
                return true;
        return false;
    }

    /**
     * Checks that the password doesn't have any spaces
     *
     * @param pass The password being checked
     * @return Whether the data is valid or not
     */
    public boolean passwordHasNoSpace(String pass) {
        for (char c : pass.toCharArray())
            if (Character.isSpaceChar(c))
                return false;
        return true;
    }

    /**
     * Checks the phone number is numbers only and the correct length
     *
     * @param num the number being checked
     * @return Whether the data is valid or not
     */
    public boolean phoneNumber(String num) {
        for (char c : num.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return (num.length() == 11);
    }

    public boolean sizeEnum(String size){
        try{
            Size.valueOf(size);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean genderEnum(String gender){
        try{
            DogGender.valueOf(gender);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean  checkBool(String isBool){
        return !isBool.equalsIgnoreCase("true") && !isBool.equalsIgnoreCase("false");
    }

    public boolean checkDouble(String value){
        try{
            Double.parseDouble(value);
        }catch(Exception e){
            return false;
        }
        return true;
    }


}

















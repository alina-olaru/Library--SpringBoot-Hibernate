package com.alina.mylibrary.config;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBCheck {
    /**
     *
     * <p>This is a class for checking and formating data in the proper way for our database</p>
     * since 2.0.0
     *
     *

     */

   static public String Stringtify(String name){

       /**
        * @param String name represents a string field from one of tables
        * @return Stringtify method returns the String formated
        * (first letter->upperCase and the rest->lowerCase)
        * since 2.0.0
        *
        *
        */

        String s1 = name.substring(0, 1).toUpperCase();
        String s2=name.substring(1).toLowerCase();
        String nameCapitalized = s1 + s2;
        return nameCapitalized;
    }

    public static boolean isValidEmailAddress(String email) {

        /**
         * @param String email represents a field from BookUser table
         *  and it represents a way of signing in
         * @return isValidEmailAddress method checkes if the String
         * has email format and returns true or false
         * since 2.0.0
         *
         *
         */


        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    public static boolean isValidPhoneNumber(String s)
    {

        /**
         * @param String s represents a field from BookUser table(phoneNumber)
         *  and it represents a not null field for signing in
         * @return isValidPhoneNumber method checkes if the String
         * has phone number format and returns true or false
         * since 2.0.0
         *
         *
         */



        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    public static boolean containNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

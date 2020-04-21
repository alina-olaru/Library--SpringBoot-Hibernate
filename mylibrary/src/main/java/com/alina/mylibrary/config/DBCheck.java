package com.alina.mylibrary.config;

public class DBCheck {

   static public String Stringtify(String name){


        String s1 = name.substring(0, 1).toUpperCase();
        String s2=name.substring(1).toLowerCase();
        String nameCapitalized = s1 + s2;
        return nameCapitalized;
    }
}

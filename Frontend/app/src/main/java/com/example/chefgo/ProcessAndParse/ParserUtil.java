package com.example.chefgo.ProcessAndParse;

public class ParserUtil {

    public static String parseName(String name){
        int end = name.indexOf(" ");
        String ret = name.substring(0,end);
        return ret;
    }

}

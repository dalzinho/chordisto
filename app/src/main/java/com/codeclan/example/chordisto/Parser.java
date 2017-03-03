package com.codeclan.example.chordisto;

/**
 * Created by user on 03/03/2017.
 */

public class Parser {

    public String[] splitString(String string){
        String[] chordArray = string.split("[, ]+");
        return chordArray;
    }
}

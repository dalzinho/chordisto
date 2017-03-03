package com.codeclan.example.chordisto;

/**
 * Created by user on 03/03/2017.
 */

public class Parser {

    public String[] splitString(String string){
        return string.split("[, ]+");
    }

    public char[] splitChordSymbol(String string){
        return string.toCharArray();
    }

    public String getRoot(String chord){
        char[] elements = splitChordSymbol(chord);
        String root = null;
        if(Character.toString(elements[0]).matches("[A-G]")){
            root = Character.toString(elements[0]);
                if(Character.toString(elements[1]).matches("[#b]")){
                    root = Character.toString(elements[0]) + Character.toString(elements[1]);
                }
        }
        return root;
    }

}

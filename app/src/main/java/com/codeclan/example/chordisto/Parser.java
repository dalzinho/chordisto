package com.codeclan.example.chordisto;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.codeclan.example.chordisto.TriadType.*;

/**
 * Created by user on 03/03/2017.
 */

public class Parser {

    //instance variables
    ArrayList<Character> elements;
    String root;
    TriadType triad;
    Pattern pattern;

    //constructicon
    public Parser(){
        pattern = Pattern.compile("([A-G][b#]?)");
    }

    //getters
    public String getRoot() {
        return root;
    }

    public ArrayList<Character> getElements() {
        return elements;
    }

    public TriadType getTriad() {
        return triad;
    }

    //los métodos
    public String[] splitString(String string) {
        return string.split("[, ]+");
    }

    public void splitChordSymbol(String string) {
        elements = new ArrayList<>();
        char[] charArray = string.toCharArray();
        for (char character : charArray) {
            elements.add(character);
        }
    }

    public void deleteElement() {
        elements.remove(0);
    }

    public void setRoot() {

        if (Character.toString(elements.get(0)).matches("[A-G][b#]?")) {
            this.root = Character.toString(elements.get(0));
            deleteElement();
            if (Character.toString(elements.get(0)).matches("[#b]")) {
                this.root += Character.toString(elements.get(1));
                deleteElement();
            }
        }
    }

    CAPTURE GROUPS!



    public void setTriad() {

        if (elements.size() == 0){
            this.triad = MAJOR;
        }
        else if(elements.get(0).equals('m')){
            this.triad = MINOR;
            deleteElement();
        }


    }
}

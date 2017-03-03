package com.codeclan.example.chordisto;

import java.util.ArrayList;

import static com.codeclan.example.chordisto.TriadType.*;

/**
 * Created by user on 03/03/2017.
 */

public class Parser {

    //instance variables
    String root;
    ArrayList<Character> elements;
    TriadType triad;

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

        this.root = null;

        if (Character.toString(elements.get(0)).matches("[A-G]")) {
            root = Character.toString(elements.get(0));
            deleteElement();
            if (Character.toString(elements.get(0)).matches("[#b]")) {
                root +=  Character.toString(elements.get(1));
                deleteElement();
            }
        }
    }



    public void setChordType(String chord) {

        if (elements.size() == 0){
            triad = MAJOR;
        }
        else if(elements.get(0).equals('m')){
            triad = MINOR;
            deleteElement();
        }


    }
}

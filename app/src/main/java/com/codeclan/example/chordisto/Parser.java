package com.codeclan.example.chordisto;

import java.util.ArrayList;

import static com.codeclan.example.chordisto.ChordType.MAJOR;

/**
 * Created by user on 03/03/2017.
 */

public class Parser {

    public String[] splitString(String string) {
        return string.split("[, ]+");
    }

    public ArrayList<Character> splitChordSymbol(String string) {
        ArrayList<Character> returnedArrayList = new ArrayList<>();
        char[] charArray = string.toCharArray();
        for (char character : charArray) {
            returnedArrayList.add(character);
        }

        return returnedArrayList;
    }

    public String getRoot(String chord) {

        ArrayList<Character> elements = splitChordSymbol(chord);

        String root = null;

        if (Character.toString(elements.get(0)).matches("[A-G]")) {
            root = Character.toString(elements.get(0));
            if (Character.toString(elements.get(1)).matches("[#b]")) {
                root = Character.toString(elements.get(0)) + Character.toString(elements.get(1));
            }
        }
        return root;
    }

    public ArrayList<Character> deleteParsedElements(ArrayList<Character> elements, String parsedElement) {
        int indicesToDelete = parsedElement.length();
        int counter = 0;

        do {
            elements.remove(counter);
            counter++;
        }
        while (counter < indicesToDelete);
        return elements;
    }

    public ChordType getChordType(String chord) {
        ArrayList<Character> elements = splitChordSymbol(chord);
        if (elements.size() == 1) {
            return MAJOR;
        } else if (elements.size() == 2) {
            if (Character.toString(elements.get(1)).matches("[#b]")) {
                return MAJOR;
            }
        }

        return null;
    }
}

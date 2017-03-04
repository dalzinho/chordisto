package com.codeclan.example.chordisto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeclan.example.chordisto.TriadType.*;

/**
 * Created by user on 03/03/2017.
 */

public class Parser {

    //instance variables
    private RootName root;
    private TriadType triad;
    private Pattern pattern;

    //constructicon
    public Parser() {
        pattern = Pattern.compile("([A-G][#b]?)([m7])?(.*)");
    }

    //getters
    public RootName getRoot() {
        return root;
    }

    public TriadType getTriad() {
        return triad;
    }

    //los métodos
    public String[] splitString(String string) {
        return string.split("[, ]+");
    }

    private void setRoot(String rootInfo) {
        String enumFormat;
        String tonality = null;
        String accidentality = null;
        Pattern rootPattern = Pattern.compile("([A-G])([b#]?)");
        Matcher m = rootPattern.matcher(rootInfo);

        if (m.matches()) {
            tonality = m.group(1);
            accidentality = m.group(2);

        }

        if (accidentality != null) {
            switch (accidentality) {
                case "#":
                    accidentality = "SHARP";
                    break;
                case "b":
                    accidentality = "FLAT";
                    break;
            }
            enumFormat = tonality + accidentality;
        } else {
            enumFormat = tonality;
        }

        root = RootName.valueOf(enumFormat);

    }

    private void setTriad(String triadInfo) {
        if (triadInfo == null) {
            triad = MAJOR;
        } else if (triadInfo.equals("m")) {
            triad = MINOR;
        } else if (triadInfo.equals("7")) {
            triad = DOMINANT;
        }
    }


    public void setVariables(String chord) {
        String triadType = null;
        String rootName = null;
        String theRest = null;
        Matcher m = pattern.matcher(chord);
        if (m.matches()) {
            rootName = m.group(1);
            triadType = m.group(2);
//            theRest = m.group(3);
        }

        setRoot(rootName);
        setTriad(triadType);
    }





/*
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



public void setTriad() {

if (elements.size() == 0){
this.triad = MAJOR;
}
else if(elements.get(0).equals('m')){
this.triad = MINOR;
deleteElement();
}


}
*/
}

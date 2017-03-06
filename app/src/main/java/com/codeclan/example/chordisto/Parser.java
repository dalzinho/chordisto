package com.codeclan.example.chordisto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeclan.example.chordisto.TriadType.*;

/**
 * Created by user on 03/03/2017.
 */

public class Parser {

    //instance variables
    private static RootName root;
    private static TriadType triad;
    private static Pattern pattern;

    //constructicon
    public Parser() {
        pattern = Pattern.compile("([A-G][b#]?)([m7])?(.*)");
    }

    //getters
    public RootName getRoot() {
        return root;
    }

    public TriadType getTriad() {
        return triad;
    }

    //los métodos
    String[] splitString(String string) {
        return string.split("[, ]+");
    }

    private static RootName setRoot(String rootInfo) {
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

        return RootName.valueOf(enumFormat);

    }

    private static TriadType setTriad(String triadInfo) {
        triad = null;
        if (triadInfo == null) {
            triad = MAJOR;
        } else if (triadInfo.equals("m")) {
            triad = MINOR;
        } else if (triadInfo.equals("7")) {
            triad = DOMINANT;
        }
        return triad;
    }


    public static Chordable[] setVariables(String chord) {
        TriadType triadType = null;
        RootName rootName = null;
        String theRest = null;
        Matcher m = pattern.matcher(chord);
        if (m.matches()) {
            rootName = setRoot(m.group(1));
            triadType = setTriad(m.group(2));
            theRest = m.group(3);
        }

        Chordable[] chordInfo = {rootName, triadType};
        return chordInfo;
    }
}

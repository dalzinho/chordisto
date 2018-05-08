package com.codeclan.example.chordisto.util;

import com.codeclan.example.chordisto.chordenums.RootName;
import com.codeclan.example.chordisto.chordenums.TriadType;
import com.codeclan.example.chordisto.model.ChordModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeclan.example.chordisto.chordenums.TriadType.*;

/**
 * Created by user on 03/03/2017.
 */

public class Parser {

    private static TriadType triad;

    public String[] splitString(String string) {
        return string.split("[, ]+");
    }

    private static RootName setRoot(String rootInfo) {
        String enumFormat;
        String tonality = null;
        String accidentality = null;
        Pattern rootPattern = Pattern.compile("([a-gA-G])([b#]?)");
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
        } else if (triadInfo.equals("o")) {
            triad = DIMINISHED;
        } else if (triadInfo.equals("ø")) {
            triad = HALFDIMINISHED;
        } else if (triadInfo.equals("+")) {
            triad = AUGMENTED;
        }
        return triad;
    }

    private boolean isFlat(String rootInfo) {
        Pattern pattern = Pattern.compile("([A-Ga-g])(b)");
        Matcher matcher = pattern.matcher(rootInfo);
        return matcher.matches();
    }

    public static String setFlatsToSharp(String rootInfo) {

        switch (rootInfo) {
            case ("Db"):
                return "C#";
            case ("Eb"):
                return "D#";
            case("Gb"):
                return "F#";
            case("Ab"):
                return "G#";
            case("Bb"):
                return "A#";
        }

        return null;
    }

    public ChordModel setVariables(String chordName) {
        TriadType triadType = null;
        RootName rootName = null;
        Pattern pattern = Pattern.compile("([A-Ga-g][b#]?)([m7oø+])?(.*)");
        Matcher m = pattern.matcher(chordName);

        if (m.matches()) {
            if (isFlat(m.group(1))){
                rootName = setRoot(setFlatsToSharp(m.group(1)));
            } else {
                rootName = setRoot(m.group(1).toUpperCase());
            }

            triadType = setTriad(m.group(2));
        }

        ChordModel chord = new ChordModel();
        chord.setRoot(rootName);
        chord.setType(triadType);
        return chord;
    }
}

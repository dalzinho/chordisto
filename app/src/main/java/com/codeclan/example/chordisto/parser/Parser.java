package com.codeclan.example.chordisto.parser;

import com.codeclan.example.chordisto.model.Chord;
import com.codeclan.example.chordisto.model.ChordInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeclan.example.chordisto.parser.TriadType.*;

/**
 * Created by user on 03/03/2017.
 */

public class Parser {


    public static String[] splitString(String string) {
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
        TriadType triad = null;
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

    private static boolean isFlat(String rootInfo) {
        Pattern pattern = Pattern.compile("([A-Ga-g])(b)");
        Matcher matcher = pattern.matcher(rootInfo);
        return matcher.matches();
    }

    private static String setFlatsToSharp(String rootInfo) {

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

    public static List<Chord> parse(String chordInput) {
        List<ChordInfo> infos = transformChordsString(chordInput);
        return transformChordInfoList(infos);

    }

    private static List<ChordInfo> transformChordsString(String chordInput) {
        List<ChordInfo> infos = new ArrayList<>();

        for (String chord : splitString(chordInput)) {
            infos.add(parseString(chord));
        }
        return infos;
    }

    private static List<Chord> transformChordInfoList(List<ChordInfo> infos) {
        List<Chord> chords = new ArrayList<>();

        for (ChordInfo info : infos) {
            chords.add(ChordBuilder.build(info));
        }
        return chords;
    }

    public static ChordInfo parseString(String chordSymbol) {
        ChordInfo info = new ChordInfo();
        Pattern pattern = Pattern.compile("([A-Ga-g][b#]?)([m7oø+])?(.*)");
        Matcher m = pattern.matcher(chordSymbol);

        if (m.matches()) {
            RootName root;
            if (isFlat(m.group(1))){
                root = setRoot(setFlatsToSharp(m.group(1)));
            } else {
                root = setRoot(m.group(1).toUpperCase());
            }
            info.setRootName(root);
            info.setTriadType(setTriad(m.group(2)));
            info.setTheRest(m.group(3));
        }

        return info;
    }
}

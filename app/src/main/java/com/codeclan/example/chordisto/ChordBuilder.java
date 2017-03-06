package com.codeclan.example.chordisto;

import java.util.ArrayList;

import static com.codeclan.example.chordisto.TriadType.*;

/**
 * Created by user on 03/03/2017.
 */

public class ChordBuilder {


    public Chord build(String chord, Pitch pitch){

        //instantiate byte array of chord tones
        ArrayList<Byte> chordTones = new ArrayList<>();

        //get root and triad info from parser, unpack and cast into usuable types
        Chordable[] chordInfo = Parser.setVariables(chord);
        RootName rootName = (RootName) chordInfo[0];
        TriadType triadType = (TriadType) chordInfo[1];

        //get offsets from offset method
        int[] offset = setChordToneOffset(triadType);

        Integer bassPitch = (pitch.getBassValue(rootName));
        Integer majorThird = pitch.getThirdOfMiddleRegister(rootName);
        Integer third = (majorThird + offset[0]);
        Integer fifth = majorThird + offset[1];
        Integer topNote = majorThird + offset[2];

        chordTones.add(bassPitch.byteValue());
        chordTones.add(third.byteValue());
        chordTones.add(fifth.byteValue());
        chordTones.add(topNote.byteValue());

        Chord returnChord = new Chord(chordTones.get(0), chordTones.get(1), chordTones.get(2), chordTones.get(3));
        return returnChord;
    }

    private int[] setChordToneOffset(TriadType triadType){

        int[] major = {0, 3, 8};
        int[] minor = {-1, 3, 8};
        int[] dominant = {0, 3, 6};
        int[] diminished = {-1, 2, 5};
        int[] halfDiminished = {-1, 2, 6};
        int[] augmented = {0, 4, 8};

        switch(triadType){
            case MAJOR:
                return major;
            case MINOR:
                return minor;
            case DOMINANT:
                return dominant;
            case DIMINISHED:
                return diminished;
            case HALFDIMINISHED:
                return halfDiminished;
            case AUGMENTED:
                return augmented;
        }

        return null;
    }

}

// Aumtumn Leaves Chords: Am, D7, G, C, F#ø, B7, Em, Em
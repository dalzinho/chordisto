package com.codeclan.example.chordisto;

import java.util.ArrayList;

import static com.codeclan.example.chordisto.TriadType.*;

/**
 * Created by user on 03/03/2017.
 */

public class ChordBuilder {


    public ArrayList<Byte> build(String chord, Pitch pitch){

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

        return chordTones;
    }

    private int[] setChordToneOffset(TriadType triadType){

        int[] major = {0, 3, 8};
        int[] minor = {-1, 3, 8};
        int[] dominant = {0, 3, 6};

        switch(triadType){
            case MAJOR:
                return major;
            case MINOR:
                return minor;
            case DOMINANT:
                return dominant;
        }

        return null;
    }

}

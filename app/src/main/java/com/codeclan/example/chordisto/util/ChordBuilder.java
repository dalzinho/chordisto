package com.codeclan.example.chordisto.util;

import com.codeclan.example.chordisto.chordenums.TriadType;
import com.codeclan.example.chordisto.model.ChordToneModel;
import com.codeclan.example.chordisto.model.ChordModel;

import java.util.ArrayList;

/**
 * Created by user on 03/03/2017.
 */

public class ChordBuilder {

    private Parser parser;
    private Pitch pitch;

    public ChordBuilder(Parser parser, Pitch pitch) {
        this.parser = parser;
        this.pitch = pitch;
    }

    public ArrayList<Byte> getPitchesAsBytes(String chord){
        ArrayList<Byte> chordTones = new ArrayList<>();

        //get root and triad info from parser, unpack and cast into usuable types
        ChordModel chordModel = parser.setVariables(chord);

        //get offsets from offset method
        int[] offset = setChordToneOffset(chordModel.getType());

        Integer bassPitch = pitch.getBassValue(chordModel.getRoot());
        Integer majorThird = pitch.getThirdOfMiddleRegister(chordModel.getRoot());
        Integer third = (majorThird + offset[0]);
        Integer fifth = majorThird + offset[1];
        Integer topNote = majorThird + offset[2];

        chordTones.add(bassPitch.byteValue());
        chordTones.add(third.byteValue());
        chordTones.add(fifth.byteValue());
        chordTones.add(topNote.byteValue());

        return chordTones;
    }

    private static int[] setChordToneOffset(TriadType triadType){

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

    public ChordToneModel build(String inputChord){
        ArrayList chordTones = getPitchesAsBytes(inputChord);
        return new ChordToneModel(chordTones);
    }
}
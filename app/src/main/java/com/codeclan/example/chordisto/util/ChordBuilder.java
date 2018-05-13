package com.codeclan.example.chordisto.util;

import com.codeclan.example.chordisto.chordenums.TriadType;
import com.codeclan.example.chordisto.model.ChordToneModel;
import com.codeclan.example.chordisto.model.ChordModel;
import com.codeclan.example.chordisto.parser.Parser;
import com.codeclan.example.chordisto.parser.RegexParser;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by user on 03/03/2017.
 */

public class ChordBuilder {

    private Pitch pitch;

    public ChordBuilder(Pitch pitch) {
        this.pitch = pitch;
    }

    private ArrayList<Byte> getPitchesAsBytes(ChordModel model){
        ArrayList<Byte> chordTones = new ArrayList<>();

        int[] offset = setChordToneOffset(model.getType());

        Integer bassPitch = pitch.getBassValue(model.getRoot());
        Integer majorThird = pitch.getThirdOfMiddleRegister(model.getRoot());
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

    public ChordToneModel build(ChordModel model){
        ArrayList<Byte> chordTones = getPitchesAsBytes(model);
        return new ChordToneModel(chordTones);
    }
}
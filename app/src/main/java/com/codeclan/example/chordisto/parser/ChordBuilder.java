package com.codeclan.example.chordisto.parser;

import com.codeclan.example.chordisto.model.Chord;
import com.codeclan.example.chordisto.model.ChordInfo;
import com.codeclan.example.chordisto.model.Pitch;
import com.codeclan.example.chordisto.player.Chordable;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by user on 03/03/2017.
 */

public class ChordBuilder {


    private static ArrayList<Byte> getPitchesAsBytes(ChordInfo chordInfo){

        ArrayList<Byte> chordTones = new ArrayList<>();
        Pitch pitch = new Pitch();

        RootName rootName = chordInfo.getRootName();
        TriadType triadType = chordInfo.getTriadType();

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

    public static Chord build(ChordInfo info){
        ArrayList chordTones = getPitchesAsBytes(info);
        return new Chord(chordTones);
    }
}

// Aumtumn Leaves Chords: Am, D7, G, C, F#ø, B7, Em, Em
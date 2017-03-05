package com.codeclan.example.chordisto;

import java.util.ArrayList;

import static com.codeclan.example.chordisto.TriadType.*;

/**
 * Created by user on 03/03/2017.
 */

public class ChordBuilder {


    public ArrayList<Integer> build(String chord, Parser parser, Pitch pitch){

        ArrayList<Integer> chordTones = new ArrayList<>();

        parser.setVariables(chord);
        RootName root = parser.getRoot();
        TriadType triad = parser.getTriad();
        int[] offset = setChordToneOffset(triad);

        chordTones.add(pitch.getBassValue(root));
        int third = pitch.getThirdOfMiddleRegister(root);

        Integer chordToneThree = (third + offset[0]);
        int chordToneFive = third + offset[1];
        int octave = third + offset[2];

        chordTones.add(chordToneThree);
        chordTones.add(chordToneFive);
        chordTones.add(octave);

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

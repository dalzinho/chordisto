package com.codeclan.example.chordisto;

import java.util.ArrayList;

import static com.codeclan.example.chordisto.TriadType.*;

/**
 * Created by user on 03/03/2017.
 */

public class ChordBuilder {

    int[] major = {0, 3, 8};
    int[] minor = {-1, 3, 8};
    int[] dominant = {0, 3, 6};
    ArrayList<Integer> chordTones;

    public ChordBuilder(){
        chordTones = new ArrayList<>();
    }

    public ArrayList<Integer> build(String chord, Parser parser, Pitch pitch){

        parser.setVariables(chord);
        RootName root = parser.getRoot();
        chordTones.add(pitch.getBassValue(root));

        if (parser.getTriad() == MAJOR){
            //making a second inversion major triad. here goes!
            int third = pitch.getThirdOfMiddleRegister(root) + major[0];
            int fifth = third + major[1];
            int octave = third + major[2];

            chordTones.add(third);
            chordTones.add(fifth);
            chordTones.add(octave);
        }

        return chordTones;
    }

}

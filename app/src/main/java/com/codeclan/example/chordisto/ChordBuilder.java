package com.codeclan.example.chordisto;

import java.util.ArrayList;

import static com.codeclan.example.chordisto.TriadType.*;

/**
 * Created by user on 03/03/2017.
 */

public class ChordBuilder {

    

    public ChordBuilder(){

    }

    public ArrayList<Integer> build(String chord, Parser parser, Pitch pitch){
        ArrayList<Integer> chordTones = new ArrayList<>();

        parser.setVariables(chord);
        RootName root = parser.getRoot();
        chordTones.add(pitch.getBassValue(root));

        if (parser.getTriad() == MAJOR){
            //making a second inversion major triad. here goes!
            int third = pitch.getThirdOfMiddleRegister(root);
            int fifth = third + 3;
            int octave = third + 8;

            chordTones.add(third);
            chordTones.add(fifth);
            chordTones.add(octave);
        }

        return chordTones;
    }

}

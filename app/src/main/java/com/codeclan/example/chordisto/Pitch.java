package com.codeclan.example.chordisto;

import java.util.ArrayList;
import static com.codeclan.example.chordisto.RootName.*;

/**
 * Created by user on 03/03/2017.
 */

public class Pitch {

    ArrayList<Integer> bassPitchNumbers;
    ArrayList<Integer> middlePitchNumbers;

    ArrayList<RootName> roots;

    public Pitch() {

        bassPitchNumbers = new ArrayList<>();
        //these ints are midi note names
        int start = 48;
        int last = 59;

        int counter = start;
        do {
            bassPitchNumbers.add(counter);
            counter++;
        } while (counter <= last);

        middlePitchNumbers = new ArrayList<>();
        start = 60;
        last = 83;

        counter = start;

        do {
            middlePitchNumbers.add(counter);
            counter++;
        } while (counter <= last);

    }

    //some getters

    public ArrayList<Integer> getBassPitchNumbers() {
        return bassPitchNumbers;
    }

    public ArrayList<Integer> getMiddlePitchNumbers() {
        return middlePitchNumbers;
    }

    public ArrayList<RootName> getRoots() {
        return roots;
    }


//        bassNote = new HashMap<>();
//        bassNote.put(C, 48);
//        bassNote.put(CSHARP, 49);
//        bassNote.put(D, 50);
//        bassNote.put(DSHARP, 51);
//        bassNote.put(E, 52);
//        bassNote.put(F, 53);
//        bassNote.put(FSHARP, 54);
//        bassNote.put(G, 55);
//        bassNote.put(GSHARP, 56);
//        bassNote.put(A, 57);
//        bassNote.put(ASHARP, 58);
//        bassNote.put(B, 59);
}
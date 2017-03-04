package com.codeclan.example.chordisto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

import static com.codeclan.example.chordisto.RootName.*;

/**
 * Created by user on 03/03/2017.
 */

public class Pitch {

    ArrayList<Integer> bassPitchNumbers;
    ArrayList<Integer> middlePitchNumbers;

    ArrayList<RootName> roots;

    public Pitch() {


        //these ints are midi note names
        int start = 48;
        int last = 59;

        int counter = start;
        do {
            bassPitchNumbers.add(counter);
            counter++;
        } while (counter <= last);

        int start = 60;
        int last = 83;

        int counter = start;
        do {
            middlePitchNumbers.add(counter);
            counter++;
        } while (counter <= last);

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
}

package com.codeclan.example.chordisto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 09/05/18.
 */

public class ChordSequence {

    private List<ChordToneModel> sequence;

    public ChordSequence() {
        sequence = new ArrayList<>();
    }

    public List<ChordToneModel> getSequence() {
        return sequence;
    }

    public void addChord(ChordToneModel chord){
        this.sequence.add(chord);
    }
}

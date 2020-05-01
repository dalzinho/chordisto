package com.codeclan.example.chordisto.player;

import android.view.View;

import com.codeclan.example.chordisto.model.ChordSequence;
import com.codeclan.example.chordisto.model.UserInput;

/**
 * Created by john on 09/05/18.
 */

public interface Player {

    void play(ChordSequence sequence, int tempo, int loops);
}

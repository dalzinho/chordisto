package com.codeclan.example.chordisto.player;

import android.support.annotation.NonNull;

import com.codeclan.example.chordisto.model.ChordToneModel;
import com.codeclan.example.chordisto.model.ChordSequence;

import org.billthefarmer.mididriver.MidiDriver;

/**
 * Created by john on 09/05/18.
 */

public class AndroidPlayer extends Thread {

    private MidiDriver midiDriver = new MidiDriver();
    private ChordSequence chords;
    private int tempo, loops;

    public AndroidPlayer(ChordSequence chords, int tempo, int loops) {
        this.chords = chords;
        this.tempo = calculateTempo(tempo);
        this.loops = loops;
    }

    @NonNull
    private Integer calculateTempo(int tempo) {
        return 60000 / (tempo);
    }


    @Override
    public void run() {
        int loopCounter = 0;
        int loopLimit = loops;

        do {

            for (ChordToneModel chord : chords.getSequence()) {
                int beatCount = 0;
                do {
                    playChord(chord);
                    try {
                        Thread.sleep(tempo);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    beatCount++;
                }
                while (beatCount < 4);
            }

            loopCounter++;

        } while (loopCounter < loopLimit);
    }

    private void playChord(ChordToneModel chord) {
        midiDriver.write(chord.getRoot());
        midiDriver.write(chord.getThird());
        midiDriver.write(chord.getFifth());
        midiDriver.write(chord.getTopNote());
    }

}

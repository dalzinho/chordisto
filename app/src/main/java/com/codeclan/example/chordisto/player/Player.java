package com.codeclan.example.chordisto.player;

import android.widget.EditText;

import com.codeclan.example.chordisto.dao.SaveLastSequenceToPreferences;
import com.codeclan.example.chordisto.model.Chord;
import com.codeclan.example.chordisto.model.Song;
import com.codeclan.example.chordisto.parser.ChordBuilder;
import com.codeclan.example.chordisto.parser.SongBuilder;

import org.billthefarmer.mididriver.MidiDriver;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;

@Module
public class Player {

    private MidiDriver midiDriver;

    @Inject
    public Player(MidiDriver md) {
        this.midiDriver = md;
    }

    public void play(Song song) {

        int loopCounter = 0;
        int loopLimit = song.getLoops();

        do {

            for (Chord currentChord : song.getSequence()) {
                int beatCount = 0;
                do {
                    playChord(currentChord);
                    try {
                        Thread.sleep(song.getTempo());
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

    private void playChord(Chord chord) {
        midiDriver.write(chord.getRoot());
        midiDriver.write(chord.getThird());
        midiDriver.write(chord.getFifth());
        midiDriver.write(chord.getTopNote());
    }

}

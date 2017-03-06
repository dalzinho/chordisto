package com.codeclan.example.chordisto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.billthefarmer.mididriver.MidiDriver;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MidiDriver midiDriver;

    private int[] config;
    private Button buttonPlayNote;
    private EditText chordsInput;
    private EditText tempoInput;
    private EditText loopsInput;
    private ArrayList<Integer> chordTones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlayNote = (Button) findViewById(R.id.buttonPlayChord);

        chordsInput = (EditText) findViewById(R.id.chord_input_area);
        tempoInput = (EditText) findViewById(R.id.tempo_input);
        loopsInput = (EditText) findViewById(R.id.loops_input);


        midiDriver = new MidiDriver();
        chordsInput.setText(SaveLastSequenceToPreferences.getStoredSequence(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        midiDriver.start();

        config = midiDriver.config();

        Log.d(this.getClass().getName(), "maxVoices: " + config[0]);
        Log.d(this.getClass().getName(), "numChannels: " + config[1]);
        Log.d(this.getClass().getName(), "sampleRate: " + config[2]);
        Log.d(this.getClass().getName(), "mixBufferSize: " + config[3]);
    }

    @Override
    protected void onPause() {
        super.onPause();
        midiDriver.stop();
    }

    private void playChord(String inputChord) {

        Pitch pitch = new Pitch();
        ChordBuilder chordBuilder = new ChordBuilder();
        Chord chord = chordBuilder.build(inputChord, pitch);
        byte[] bassRoot;
        byte[] third;
        byte[] fifth;
        byte[] topNote;

        Integer velocity = 127;
        bassRoot = new byte[3];
        bassRoot[0] = (byte) (0x90 | 0x00);
        bassRoot[1] = chord.root();  //this byteValue business takes the note int and converts it so i don't have to
        bassRoot[2] = velocity.byteValue();

        third = new byte[3];
        third[0] = (byte) (0x90 | 0x00);
        third[1] = chord.third();
        third[2] = velocity.byteValue();

        fifth = new byte[3];
        fifth[0] = (byte) (0x90 | 0x00);
        fifth[1] = chord.fifth();
        fifth[2] = velocity.byteValue();

        topNote = new byte[3];
        topNote[0] = (byte) (0x90 | 0x00);
        topNote[1] = chord.topNote();
        topNote[2] = velocity.byteValue();

        midiDriver.write(bassRoot);
        midiDriver.write(third);
        midiDriver.write(fifth);
        midiDriver.write(topNote);


    }

    public void playMusic(View button) {


        Integer tempo = 60000 / (Integer.parseInt(tempoInput.getText().toString()));

        String[] playTheseChords = Parser.splitString(chordsInput.getText().toString());
        int loopCounter = 0;
        int loopLimit = Integer.parseInt(loopsInput.getText().toString());

        do {

            for (String currentChord : playTheseChords) {
                int beatCount = 0;
                do {
                    playChord(currentChord);
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

        SaveLastSequenceToPreferences.setStoredSequence(this,chordsInput.getText().toString());
    }

}




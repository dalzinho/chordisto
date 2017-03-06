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

public class MainActivity extends AppCompatActivity implements MidiDriver.OnMidiStartListener,
        View.OnTouchListener {

    private MidiDriver midiDriver;

    private int[] config;
    private Button buttonPlayNote;
    private EditText chordsInput;
    private EditText tempoInput;
    private ArrayList<Integer> chordTones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlayNote = (Button) findViewById(R.id.buttonPlayChord);
        buttonPlayNote.setOnTouchListener(this);

        chordsInput = (EditText)findViewById(R.id.chord_input_area);
        tempoInput = (EditText)findViewById(R.id.tempo_input);

        midiDriver = new MidiDriver();
        midiDriver.setOnMidiStartListener(this);
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

    @Override
    public void onMidiStart() {
        Log.d(this.getClass().getName(), "onMidiStart()");
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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        Log.d(this.getClass().getName(), "Motion event: " + motionEvent);

        if (view.getId() == R.id.buttonPlayChord) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");

                Integer tempo = 60000 / (Integer.parseInt(tempoInput.getText().toString()));

                String[] playTheseChords = Parser.splitString(chordsInput.getText().toString());
                for (String currentChord : playTheseChords){
                    playChord(currentChord);
                    try {
                        Thread.sleep(tempo);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playChord(currentChord);
                    try {
                        Thread.sleep(tempo);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playChord(currentChord);
                    try {
                        Thread.sleep(tempo);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playChord(currentChord);
                    try {
                        Thread.sleep(tempo);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            return false;
        }
    }

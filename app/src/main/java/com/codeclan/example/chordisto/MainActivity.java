package com.codeclan.example.chordisto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.billthefarmer.mididriver.MidiDriver;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    private MidiDriver midiDriver;
    private TextView songTitle;
    private EditText chordsInput;
    private EditText tempoInput;
    private EditText loopsInput;
    private Pitch pitch;
    private BottomNavigationView bottomNavigationMenu;

    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new DatabaseHandler(this);
        setContentView(R.layout.activity_main);

        chordsInput = (EditText)findViewById(R.id.chord_input_area);
        tempoInput = (EditText)findViewById(R.id.tempo_input);
        loopsInput = (EditText)findViewById(R.id.loops_input);
        songTitle = (TextView)findViewById(R.id.song_title);
        bottomNavigationMenu = (BottomNavigationView) findViewById(R.id.bottom_nav);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
//        if (extras.containsKey("id")){
//            int songId = extras.getInt("id");
//            Song song = dbHandler.getSong(songId);
//            songTitle.setText(song.getSongTitle());
//            chordsInput.setText(song.getChords());
//            tempoInput.setText(String.valueOf(song.getTempo()));
//        }

        midiDriver = new MidiDriver();
        pitch = new Pitch();
        dbHandler = new DatabaseHandler(this);

        chordsInput.setText(SaveLastSequenceToPreferences.getStoredSequence(this));
        bottomNavigationMenu.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        midiDriver.start();

        int[] config = midiDriver.config();

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
        Chord chord = ChordBuilder.build(inputChord, pitch);
        midiDriver.write(chord.getRoot());
        midiDriver.write(chord.getThird());
        midiDriver.write(chord.getFifth());
        midiDriver.write(chord.getTopNote());

    }

    private String[] getUsableVariables(){
        String[] variables = {
                songTitle.getText().toString(),
                chordsInput.getText().toString(),
                tempoInput.getText().toString()
        };

        return variables;
    }

    public void playMusic(View button) {


        Integer tempo = 60000 / (Integer.parseInt(tempoInput.getText().toString()));
        String[] variables = getUsableVariables();

        Song song = SongBuilder.run(variables[0], variables[1], Integer.parseInt(variables[2]));

        int loopCounter = 0;
        int loopLimit = Integer.parseInt(loopsInput.getText().toString());

        do {

            for (String currentChord : song.getChordsAsArray()) {
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


    public void saveToSongBook(View button){
        String chords = chordsInput.getText().toString();
        int tempo = Integer.parseInt(tempoInput.getText().toString());

        Intent intent = new Intent(this, SaveAndEditActivity.class);
        intent.putExtra("chords", chords);
        intent.putExtra("tempo", tempo);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        String toastText = null;

        switch (item.getItemId()) {
            case R.id.music_screen_button:
                toastText = "You clicked music";
                break;
            case R.id.songbook_button:
                Intent intent = new Intent(this, SongbookActivity.class);
                startActivity(intent);
                break;

        }

        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
        return true;
    }

}




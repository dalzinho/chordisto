package com.codeclan.example.chordisto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


//import com.facebook.stetho.Stetho;

import com.codeclan.example.chordisto.parser.ChordBuilder;
import com.codeclan.example.chordisto.model.Pitch;
import com.codeclan.example.chordisto.R;
import com.codeclan.example.chordisto.model.Song;
import com.codeclan.example.chordisto.parser.Parser;
import com.codeclan.example.chordisto.parser.SongBuilder;
import com.codeclan.example.chordisto.dao.DatabaseHandler;
import com.codeclan.example.chordisto.dao.SaveLastSequenceToPreferences;
import com.codeclan.example.chordisto.model.Chord;
import com.codeclan.example.chordisto.player.Player;
import com.codeclan.example.chordisto.util.Helper;

import org.billthefarmer.mididriver.MidiDriver;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    @Inject
    private MidiDriver midiDriver;

    private Player player;

    @Inject
    MainActivity(Player player) {
        this.player = player;
    }

    private TextView songTitle;
    private EditText chordsInput;
    private EditText tempoInput;
    private EditText loopsInput;
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
        if (extras != null && extras.containsKey("id")){
            int songId = extras.getInt("id");
            Song song = dbHandler.getSong(songId);
            songTitle.setText(song.getSongTitle());
            chordsInput.setText(song.getChords());
            tempoInput.setText(String.valueOf(song.getTempo()));
        }

        midiDriver = new MidiDriver();
        dbHandler = new DatabaseHandler(this);

        chordsInput.setText(SaveLastSequenceToPreferences.getStoredSequence(this));
        tempoInput.setText(String.valueOf(SaveLastSequenceToPreferences.getStoredTempo(this)));
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

    public void playMusic(View button) {

        player.play(songFromInputs());
        SaveLastSequenceToPreferences.setStoredSequence(this,chordsInput.getText().toString(), Integer.parseInt(tempoInput.getText().toString()));
    }

    private Song songFromInputs() {
        Song song = new Song();
        int tempo = Helper.calculateTempo(tempoInput.getText().toString());
        List<Chord> chords = Parser.parse(chordsInput.getText().toString());
        int loops = Integer.parseInt(loopsInput.getText().toString());

        song.setTempo(tempo);
        song.setSequence(chords);
        song.setLoops(loops);

        return song;
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
                break;
            case R.id.songbook_button:
                Intent intent = new Intent(this, SongbookActivity.class);
                startActivity(intent);
                break;

        }
        return false;
    }

}




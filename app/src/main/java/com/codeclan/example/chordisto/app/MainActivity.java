package com.codeclan.example.chordisto.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codeclan.example.chordisto.android.activity.SaveAndEditActivity;
import com.codeclan.example.chordisto.android.activity.SongbookActivity;
import com.codeclan.example.chordisto.model.ChordModel;
import com.codeclan.example.chordisto.model.ChordSequence;
import com.codeclan.example.chordisto.model.UserInput;
import com.codeclan.example.chordisto.parser.Parser;
import com.codeclan.example.chordisto.player.AndroidPlayer;
import com.codeclan.example.chordisto.parser.RegexParser;
import com.codeclan.example.chordisto.player.Player;
import com.codeclan.example.chordisto.util.Pitch;
import com.codeclan.example.chordisto.R;
import com.codeclan.example.chordisto.android.util.SaveLastSequenceToPreferences;
import com.codeclan.example.chordisto.model.Song;
import com.codeclan.example.chordisto.db.DatabaseHandler;
import com.codeclan.example.chordisto.util.ChordBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    private TextView songTitle;
    private EditText chordsInput;
    private EditText tempoInput;
    private EditText loopsInput;
    private Player player;
    private BottomNavigationView bottomNavigationMenu;
    private ChordBuilder chordBuilder;
    private Parser parser;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new DatabaseHandler(this);
        chordBuilder = new ChordBuilder(new Pitch());
        parser = new RegexParser();
        Intent intent = getIntent();

        setContentView(R.layout.activity_main);
        chordsInput = (EditText)findViewById(R.id.chord_input_area);
        tempoInput = (EditText)findViewById(R.id.tempo_input);
        loopsInput = (EditText)findViewById(R.id.loops_input);
        songTitle = (TextView)findViewById(R.id.song_title);
        bottomNavigationMenu = (BottomNavigationView) findViewById(R.id.bottom_nav);

        Bundle extras = intent.getExtras();
        if (extras != null && extras.containsKey("id")){
            int songId = extras.getInt("id");
            Song song = dbHandler.getSong(songId);
            songTitle.setText(song.getSongTitle());
            chordsInput.setText(song.getChords());
            tempoInput.setText(String.valueOf(song.getTempo()));
        }

        dbHandler = new DatabaseHandler(this);

        chordsInput.setText(SaveLastSequenceToPreferences.getStoredSequence(this));
        tempoInput.setText(String.valueOf(SaveLastSequenceToPreferences.getStoredTempo(this)));
        bottomNavigationMenu.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.restart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    private UserInput getUserInputFromUI() {
        UserInput input = new UserInput();
        input.setTitle(songTitle.getText().toString());
        input.setChords(chordsInput.getText().toString());
        input.setTempo(tempoInput.getText().toString());
        input.setLoopCount(loopsInput.getText().toString());
        return input;
    }
    
    public void playMusic(View button) {
        UserInput input = getUserInputFromUI();

        if(input.getChords().isEmpty()){
            input.setChords("C");
        }
        if(input.getTempo().isEmpty()){
            input.setTempo(String.valueOf(100));
        }
        if(input.getLoopCount().isEmpty()){
            input.setLoopCount((String.valueOf(1)));
        }

        List<ChordModel> chordModels = parser.getChordModelsFromStringInput(input.getChords());

        ChordSequence sequence = new ChordSequence();

        for (ChordModel model : chordModels) {
            sequence.addChord(chordBuilder.build(model));
        }

        player = new AndroidPlayer(sequence, Integer.parseInt(input.getTempo()), Integer.parseInt(input.getLoopCount()));
        player.start();

        SaveLastSequenceToPreferences.setStoredSequence(this,chordsInput.getText().toString(), Integer.parseInt(tempoInput.getText().toString()));
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




package com.codeclan.example.chordisto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SaveAndEditActivity extends AppCompatActivity {

    DatabaseHandler db;
    EditText songTitle;
    EditText chords;
    EditText tempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_and_edit_activty);
        songTitle = (EditText)findViewById(R.id.song_title);
        chords = (EditText)findViewById(R.id.chord_input_area);
        tempo = (EditText)findViewById(R.id.tempo_input);


        db = new DatabaseHandler(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String intentChords = extras.getString("chords");
        int intentTempo = extras.getInt("tempo");

        
        chords.setText(intentChords);
        tempo.setText(intentTempo);


    }
}

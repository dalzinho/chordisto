package com.codeclan.example.chordisto;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SongbookActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    ListView listView;
    String[] songTitles;
    BottomNavigationView bottomNavigationView;
    ArrayList<String> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songbook);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        DatabaseHandler db = new DatabaseHandler(this);

        ArrayList<Song> songList = db.getAllSongs();
        songs = new ArrayList<>();

        for (Song song : songList) {
            songs.add(song.getSongTitle());
        }

        listView = (ListView) findViewById(R.id.songbook_list_view);

        ArrayAdapter<String> titlesAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                songs);

        listView.setAdapter(titlesAdapter);
        listView.setOnItemClickListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        String toastText = null;

        switch (item.getItemId()) {
            case R.id.music_screen_button:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            case R.id.songbook_button:
                toastText = "You RAAAAANNGGG?!";
                break;

        }

        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        


//        String name = songs.get(position);
//        String toastText = "The name " + name + " was clicked.";
//        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }
}
package com.codeclan.example.chordisto.activity;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codeclan.example.chordisto.R;
import com.codeclan.example.chordisto.model.Song;
import com.codeclan.example.chordisto.dao.DatabaseHandler;

import java.util.ArrayList;

public class SongbookActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private BottomNavigationView bottomNavigationView;
    private ArrayList<String> songs;
    private ArrayList<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songbook);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        DatabaseHandler db = new DatabaseHandler(this);

        songList = db.getAllSongs();
        songs = new ArrayList<>();

        for (Song song : songList) {
            songs.add(song.getSongTitle() + " " + String.valueOf(song.getId()));
        }

        listView = (ListView) findViewById(R.id.songbook_list_view);

        ArrayAdapter<String> titlesAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                songs);

        listView.setAdapter(titlesAdapter);
        listView.setOnItemClickListener(this);


    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        String toastText = null;
//
//        switch (item.getItemId()) {
//            case R.id.music_screen_button:
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//            case R.id.songbook_button:
//                toastText = "You RAAAAANNGGG?!";
//                break;
//
//        }
//
//        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
//        return true;
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int songId = listView.getPositionForView(view) + 1;

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("id", songId);
        startActivity(intent);


    }
}
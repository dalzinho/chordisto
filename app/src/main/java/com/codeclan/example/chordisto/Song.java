package com.codeclan.example.chordisto;

/**
 * Created by user on 06/03/2017.
 */

public class Song {

    String title;
    String chords;

    public Song(String title, String chords){
        this.title = title;
        this.chords = chords;
    }

    public String getTitle() {
        return title;
    }

    public String getChords() {
        return chords;
    }
}

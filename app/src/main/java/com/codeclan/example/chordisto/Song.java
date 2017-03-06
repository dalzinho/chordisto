package com.codeclan.example.chordisto;

/**
 * Created by user on 06/03/2017.
 */

public class Song {

    String title;
    String chords;
    int id;

    public Song(int id, String title, String chords){
        this.id = id;
        this.title = title;
        this.chords = chords;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getChords() {
        return chords;
    }

    //setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setChords(String chords) {
        this.chords = chords;
    }

    public void setId(int id) {
        this.id = id;
    }
}

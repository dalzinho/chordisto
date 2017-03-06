package com.codeclan.example.chordisto;

/**
 * Created by user on 06/03/2017.
 */

public class Song {

    String songTitle;
    String chords;
    int id;

    public Song(){
        //hooray for method overloading.
    }

    public Song(int id, String songTitle, String chords){
        this.id = id;
        this.songTitle = songTitle;
        this.chords = chords;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getChords() {
        return chords;
    }

    //setters

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public void setChords(String chords) {
        this.chords = chords;
    }

    public void setId(int id) {
        this.id = id;
    }
}

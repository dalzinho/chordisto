package com.codeclan.example.chordisto;

/**
 * Created by user on 06/03/2017.
 */

public class Song {

    int id;
    String songTitle;
    String chords;
    int tempo;
    int loops;

    public Song(){
        //hooray for method overloading.
    }

    public Song(int id, String songTitle, String chords,  int tempo, int loops){
        this.id = id;
        this.songTitle = songTitle;
        this.chords = chords;
        this.tempo = tempo;
        this.loops = loops;
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

    public int getTempo() {
        return tempo;
    }

    public int getLoops() {
        return loops;
    }

    //setters

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public void setChords(String chords) {
        this.chords = chords;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void setLoops(int loops) {
        this.loops = loops;
    }

    public void setId(int id) {
        this.id = id;


    }
}

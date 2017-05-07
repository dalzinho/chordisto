package com.codeclan.example.chordisto;

/**
 * Created by user on 06/03/2017.
 */

public class Song {

    int id;
    String songTitle;
    String chords;
    int tempo;

    public Song(){
        //hooray for method overloading.
    }

    public Song(int id, String songTitle, String chords,  int tempo){
        this.id = id;
        this.songTitle = songTitle;
        this.chords = chords;
        this.tempo = tempo;
    }

    public Song(String songTitle, String chords,  int tempo){
        this.songTitle = songTitle;
        this.chords = chords;
        this.tempo = tempo;
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
    public String[] getChordsAsArray() {
        String[] arrayOfChords = Parser.splitString(chords);
        return arrayOfChords;
    }

    public int getTempo() {
        return tempo;
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

    public void setId(int id) {
        this.id = id;


    }
}

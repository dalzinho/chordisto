package com.codeclan.example.chordisto.model;

/**
 * Created by john on 09/05/18.
 */

public class UserInput {
    int id;
    String title;
    String chords;
    String tempo;
    String loopCount;

    public void setChords(String chords) {
        this.chords = chords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChords() {
        return chords;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getLoopCount() {
        return loopCount;
    }

    public void setLoopCount(String loopCount) {
        this.loopCount = loopCount;
    }
}

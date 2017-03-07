package com.codeclan.example.chordisto;

/**
 * Created by user on 07/03/2017.
 */

public class SongBuilder {

    private static int id;
    private static String title;
    private static String chords;
    private int tempo;

    public SongBuilder(){
        title = "Untitled Song";
        chords = "";
        tempo = 100;
    }

    public static Song run(String inputTitle, String inputChords, int inputTempo){

        return new Song(inputTitle, inputChords, inputTempo);
    }

    public static Song run(int id, String inputTitle, String inputChords, int inputTempo){

        return new Song(id, inputTitle, inputChords, inputTempo);
        
    }
}

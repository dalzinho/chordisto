package com.codeclan.example.chordisto;

/**
 * Created by user on 06/03/2017.
 */

public class Chord {

    private byte root;
    private byte third;
    private byte fifth;
    private byte topNote;

    public Chord(byte root, byte third, byte fifth, byte topNote){
        this.root = root;
        this.third = third;
        this.fifth = fifth;
        this.topNote = topNote;
    }
}

package com.codeclan.example.chordisto;

import java.util.ArrayList;

/**
 * Created by user on 06/03/2017.
 */

public class Chord {

    private byte[] root;
    private byte[] third;
    private byte[] fifth;
    private byte[] topNote;

    public Chord(ArrayList<Byte> chordTones){

        Integer velocity = 63;

        root = new byte[3];
        root[0] = (byte) (0x90 | 0x00);
        root[1] = chordTones.get(0);
        root[2] = velocity.byteValue();

        third = new byte[3];
        third[0] = (byte) (0x90 | 0x00);
        third[1] = chordTones.get(1);
        third[2] = velocity.byteValue();

        fifth = new byte[3];
        fifth[0] = (byte) (0x90 | 0x00);
        fifth[1] = chordTones.get(2);
        fifth[2] = velocity.byteValue();

        topNote = new byte[3];
        topNote[0] = (byte) (0x90 | 0x00);
        topNote[1] = chordTones.get(3);
        topNote[2] = velocity.byteValue();

    }

    public byte[] getRoot() {
        return root;
    }

    public byte[] getThird() {
        return third;
    }

    public byte[] getFifth() {
        return fifth;
    }

    public byte[] getTopNote() {
        return topNote;
    }
}

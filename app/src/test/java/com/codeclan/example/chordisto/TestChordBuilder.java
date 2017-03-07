package com.codeclan.example.chordisto;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by user on 03/03/2017.
 */

public class TestChordBuilder {

    Pitch pitch;
    ChordBuilder chordBuilder;

    @Before
    public void setup(){
        pitch = new Pitch();
        chordBuilder = new ChordBuilder();
    }

    @Test
    public void testAskingForChordReturnsFourNumbers(){
        ArrayList<Byte> chordTones = chordBuilder.getPitchesAsBytes("C", pitch);
        assertEquals(4, chordTones.size());
    }

    @Test
    public void testAskingForMajorChordReturnsTheCorrectNumbers(){
        ArrayList<Byte> chordTones = chordBuilder.getPitchesAsBytes("D", pitch);
        assertTrue(chordTones.contains((byte) 0x32));
    }

    @Test
    public void testPullsCorrectTonesForMinorChords(){
        ArrayList<Byte> chordTones = chordBuilder.getPitchesAsBytes("Em", pitch);
        assertTrue(chordTones.contains((byte) 0x34));
    }

    @Test
    public void testReturnsDominantMidiNotes(){
        ArrayList<Byte> chordTones = chordBuilder.getPitchesAsBytes("G7", pitch);
        assertTrue(chordTones.contains((byte) 0x37));

    }

}

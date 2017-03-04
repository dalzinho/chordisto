package com.codeclan.example.chordisto;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.codeclan.example.chordisto.RootName.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by user on 03/03/2017.
 */

public class TestChord {

    Pitch pitch;
    Parser parser;
    ChordBuilder chordBuilder;

    @Before
    public void setup(){
        pitch = new Pitch();
        parser = new Parser();
        chordBuilder = new ChordBuilder();
    }

    @Test
    public void testAskingForChordReturnsFourNumbers(){
        ArrayList<Integer> chordTones = chordBuilder.build("C", parser, pitch);
        assertEquals(4, chordTones.size());
    }

    @Test
    public void testAskingForMajorChordReturnsTheCorrectNumbers(){
        ArrayList<Integer> chordTones = chordBuilder.build("D", parser, pitch);
        assertTrue(chordTones.contains(50));
        assertTrue(chordTones.contains(66));
        assertTrue(chordTones.contains(69));
        assertTrue(chordTones.contains(74));
    }

    @Test
    public void testPullsCorrectTonesForMinorChords(){

    }

}

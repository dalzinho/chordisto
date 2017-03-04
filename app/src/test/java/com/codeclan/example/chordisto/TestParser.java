package com.codeclan.example.chordisto;

import org.junit.Before;
import org.junit.Test;

import static com.codeclan.example.chordisto.RootName.*;
import static com.codeclan.example.chordisto.TriadType.*;
import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 03/03/2017.
 */

public class TestParser {

    Parser parser;

    @Before
    public void setup() {
        parser = new Parser();
    }

    @Test
    public void testCanSeparateInputIntoArray() {
        String chords = "C, G, F, G";
//        String[] chordArray = parser.splitString(chords);
        assertEquals(4, parser.splitString(chords).length);
    }

    @Test
    public void testRegexGetsSimpleRoot() {
        String chord = "C";
        parser.setVariables(chord);
        assertEquals(C, parser.getRoot());
    }

    @Test
    public void testRegexGetsRootWithAccidental() {
        String chord = "Bb";
        parser.setVariables(chord);
        assertEquals(BFLAT, parser.getRoot());
    }

    @Test
    public void testRootCanBeSharp() {
        String otherChord = "F#";
        parser.setVariables(otherChord);
        assertEquals(FSHARP, parser.getRoot());
    }


    @Test
    public void testGetRootFromLongerSymbol() {
        String chord = "Gm";
        parser.setVariables(chord);
        assertEquals(G, parser.getRoot());
    }

    @Test
    public void testSetTriadMAJOREdition() {
        String chord = "G";
        parser.setVariables(chord);
        assertEquals(MAJOR, parser.getTriad());
    }

    @Test
    public void testSetTriadMinorEdition() {
        String chord = "Gm";
        parser.setVariables(chord);
        assertEquals(MINOR, parser.getTriad());
    }

    @Test
    public void testSetDominantTriad() {
        String chord = "F#7";
        parser.setVariables(chord);
        assertEquals(DOMINANT, parser.getTriad());
    }

    @Test
    public void testDoesNotSpitDummyWhenMoreInfoGivenThanExpected(){
        String chord = "Db7#9b11";
        parser.setVariables(chord);
        assertEquals(DFLAT, parser.getRoot());
        assertEquals(DOMINANT, parser.getTriad());
    }

//      I am presently several Regex skill levels away from being able to do this at the moment.

//    @Test
//    public void testTermMajIncludedInNameDoesNotResolveMinor(){
//        String chord = "Ebmaj7";
//        parser.setVariables(chord);
//        assertEquals(MAJOR, parser.getTriad());
//    }
}
package com.codeclan.example.chordisto;

import com.codeclan.example.chordisto.model.ChordModel;
import com.codeclan.example.chordisto.util.Parser;

import org.junit.Before;
import org.junit.Test;

import static com.codeclan.example.chordisto.chordenums.RootName.*;
import static com.codeclan.example.chordisto.chordenums.TriadType.*;
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
    public void testRegexGetsSimpleRoot() {
        String chordName = "C";
        ChordModel chord = parser.setVariables(chordName);
        assertEquals(C, chord.getRoot());
    }

    @Test
    public void testRegexGetsRootWithAccidental() {
        String chordName = "A#";
        ChordModel chord = parser.setVariables(chordName);
        assertEquals(ASHARP, chord.getRoot());
    }

    @Test
    public void testGetRootFromLongerSymbol() {
        String chordName = "Gm";
        ChordModel chord = parser.setVariables(chordName);
        assertEquals(G, chord.getRoot());
    }

    @Test
    public void testSetTriadMAJOREdition() {
        String chordName = "G";
        ChordModel chord = parser.setVariables(chordName);
        assertEquals(MAJOR, chord.getType());
    }

    @Test
    public void testSetTriadMinorEdition() {
        String chordName = "Gm";
        ChordModel chord = parser.setVariables(chordName);

        assertEquals(MINOR, chord.getType());
    }

    @Test
    public void testSetDominantTriad() {
        String chordName = "F#7";
        ChordModel chord = parser.setVariables(chordName);
        assertEquals(DOMINANT, chord.getType());
    }

    @Test
    public void testDoesNotSpitDummyWhenMoreInfoGivenThanExpected(){
        String chordName = "D#7#9b11";
        ChordModel chord = parser.setVariables(chordName);

        assertEquals(DSHARP, chord.getRoot());
        assertEquals(DOMINANT, chord.getType());
    }

    @Test
    public void testSetFlatsToSharp(){
        assertEquals("C#", Parser.setFlatsToSharp("Db"));
    }

    @Test
    public void testReadsFlatsAndCreatesSharpEnums(){
        String chordName = "Db";
        ChordModel chord = parser.setVariables(chordName);
        assertEquals(CSHARP, chord.getRoot());
    }

//      I am presently several Regex skill levels away from being able to do this at the moment.

//    @Test
//    public void testTermMajIncludedInNameDoesNotResolveMinor(){
//        String chord = "Ebmaj7";
//        parser.setVariables(chord);
//        assertEquals(MAJOR, parser.getTriad());
//    }
}
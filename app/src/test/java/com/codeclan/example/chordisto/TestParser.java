package com.codeclan.example.chordisto;

import org.junit.Before;
import org.junit.Test;

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
    public void testRegexGetsRoot(){
        String chord = "F#m";
        parser.run(chord);
        assertEquals("F#", parser.getRoot());
    }
//    @Test
//    public void testCanBreakSymbolIntoChunks() {
//        String chord = "F7";
//        assertEquals(2, parser.getElements().size());
//
//    }
//
//    @Test
//    public void testCanDeleteRootNoteFromSimpleChord(){
//        String chord = "Bb7";
//        parser.splitChordSymbol(chord);
//        parser.deleteElement();
//        assertEquals(2, parser.getElements().size());
//    }
//
//    @Test
//    public void testUseRegexToPullRootNote() {
//        String chord = "F7b9";
//        parser.splitChordSymbol(chord);
//        parser.setRoot();
//        assertEquals("F", parser.getRoot());
//    }
//
//    @Test
//    public void testUseRegexToPullAccidentalRootNote(){
//        String chord = "F#7b9";
//        parser.splitChordSymbol(chord);
//        parser.setRoot();
//        assertEquals("F#", parser.getRoot());
//    }
//
//    @Test
//    public void testGetRootNoteFromLudicrouslyComplexChordSymbol(){
//        String chord = "Bb7b11b13";
//        parser.splitChordSymbol(chord);
//        assertEquals("Bb", parser.getRoot());
//    }
//
//    @Test
//    public void testGetChordTypeMajorNonAccidental(){
//        String chord = "C";
//        assertEquals(MAJOR, parser.getTriad());
//    }
//
//    @Test
//    public void testRecognisesMinorChords(){
//        String chord = "Cm";
//        parser.setRoot();
//        parser.setTriad();
//        assertEquals(MINOR, parser.getTriad());
//    }
}
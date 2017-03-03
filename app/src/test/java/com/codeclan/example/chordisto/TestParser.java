package com.codeclan.example.chordisto;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.codeclan.example.chordisto.ChordType.MAJOR;
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
    public void testCanBreakSymbolIntoChunks() {
        String chord = "F7";
        assertEquals(2, parser.splitChordSymbol(chord).size());

    }

    @Test
    public void testUseRegexToPullRootNote() {
        String chord = "F7b9";
        assertEquals("F", parser.getRoot(chord));
    }

    @Test
    public void testUseRegexToPullAccidentalRootNote(){
        String chord = "F#7b9";
        assertEquals("F#", parser.getRoot(chord));
    }

    @Test
    public void testGetRootNoteFromLudicrouslyComplexChordSymbol(){
        String chord = "Bb7b11b13";
        assertEquals("Bb", parser.getRoot(chord));
    }

    @Test
    public void testGetChordTypeMajorNonAccidental(){
        String chord = "C";
        assertEquals(MAJOR, parser.getChordType(chord));
    }

    @Test
    public void testCanDeleteRootNoteFromSimpleChord(){
        String chord = "Bb7";
        ArrayList<Character> elements = parser.splitChordSymbol(chord);
        assertEquals(1, parser.deleteParsedElements(elements, "Bb").size());
    }

    @Test
    public void testRecognisesMinorChords(){
        
    }
}
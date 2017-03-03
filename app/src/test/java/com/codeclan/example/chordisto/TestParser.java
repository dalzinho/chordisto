package com.codeclan.example.chordisto;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 03/03/2017.
 */

public class TestParser {

    Parser parser;

    @Before
    public void setup(){
        parser = new Parser();
    }

    @Test
    public void testCanSeparateInputIntoArray(){
        String chords = "C, G, F, G";
//        String[] chordArray = parser.splitString(chords);
        assertEquals(4, parser.splitString(chords).length);
    }

    @Test
    public void testCanBreakSymbolIntoChars(){
        String chord = "F7b9";
        assertEquals(4, parser.splitChordSymbol(chord).length);
    }

}

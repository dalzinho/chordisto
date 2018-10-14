//package com.codeclan.example.chordisto;
//
//import com.codeclan.example.chordisto.parser.Parser;
//import com.codeclan.example.chordisto.parser.RootName;
//import com.codeclan.example.chordisto.player.Chordable;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import static com.codeclan.example.chordisto.parser.RootName.*;
//import static com.codeclan.example.chordisto.parser.TriadType.*;
//import static junit.framework.Assert.assertEquals;
//
///**
// * Created by user on 03/03/2017.
// */
//
//public class TestParser {
//
//    Parser parser;
//
//    @Before
//    public void setup() {
//        parser = new Parser();
//    }
//
//    @Test
//    public void testCanSeparateInputIntoArray() {
//        String chords = "C, G, F, G";
////        String[] chordArray = parser.splitString(chords);
//        assertEquals(4, parser.splitString(chords).length);
//    }
//
//    @Test
//    public void testRegexGetsSimpleRoot() {
//        String chord = "C";
//        Chordable[] chordInfo = Parser.parseString(chord);
//        assertEquals(C, (RootName) chordInfo[0]);
//    }
//
//    @Test
//    public void testRegexGetsRootWithAccidental() {
//        String chord = "A#";
//        Chordable[] chordInfo = Parser.parseString(chord);
//        assertEquals(ASHARP, chordInfo[0]);
//    }
//
//    @Test
//    public void testRootCanBeSharp() {
//        String chord = "F#";
//        Chordable[] chordInfo = Parser.parseString(chord);
//        assertEquals(FSHARP, chordInfo[0]);
//    }
//
//
//    @Test
//    public void testGetRootFromLongerSymbol() {
//        String chord = "Gm";
//        Chordable[] chordInfo = Parser.parseString(chord);
//
//        assertEquals(G, chordInfo[0]);
//    }
//
//    @Test
//    public void testSetTriadMAJOREdition() {
//        String chord = "G";
//        Chordable[] chordInfo = Parser.parseString(chord);
//
//        assertEquals(MAJOR, chordInfo[1]);
//    }
//
//    @Test
//    public void testSetTriadMinorEdition() {
//        String chord = "Gm";
//        Chordable[] chordInfo = Parser.parseString(chord);
//
//        assertEquals(MINOR, chordInfo[1]);
//    }
//
//    @Test
//    public void testSetDominantTriad() {
//        String chord = "F#7";
//        Chordable[] chordInfo = Parser.parseString(chord);
//
//        assertEquals(DOMINANT, chordInfo[1]);
//    }
//
//    @Test
//    public void testDoesNotSpitDummyWhenMoreInfoGivenThanExpected(){
//        String chord = "D#7#9b11";
//        Chordable[] chordInfo = Parser.parseString(chord);
//
//        assertEquals(DSHARP, chordInfo[0]);
//        assertEquals(DOMINANT, chordInfo[1]);
//    }
//    @Test
//    public void testMatcherRecognisesFlats(){
//        assertEquals(true, Parser.isFlat("Db"));
//        assertEquals(false, Parser.isFlat("C#"));
//    }
//
//    @Test
//    public void testSetFlatsToSharp(){
//        assertEquals("C#", Parser.setFlatsToSharp("Db"));
//    }
//
//    @Test
//    public void testReadsFlatsAndCreatesSharpEnums(){
//        String chord = "Db";
//        Chordable[] chordInfo = Parser.parseString(chord);
//        assertEquals(CSHARP, chordInfo[0]);
//    }
//
//
////      I am presently several Regex skill levels away from being able to do this at the moment.
//
////    @Test
////    public void testTermMajIncludedInNameDoesNotResolveMinor(){
////        String chord = "Ebmaj7";
////        parser.parseString(chord);
////        assertEquals(MAJOR, parser.getTriad());
////    }
//}
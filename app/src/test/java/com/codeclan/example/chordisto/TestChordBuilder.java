package com.codeclan.example.chordisto;

import com.codeclan.example.chordisto.model.ChordModel;
import com.codeclan.example.chordisto.model.ChordToneModel;
import com.codeclan.example.chordisto.parser.Parser;
import com.codeclan.example.chordisto.parser.RegexParser;
import com.codeclan.example.chordisto.util.Pitch;
import com.codeclan.example.chordisto.util.ChordBuilder;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;

import static junit.framework.Assert.assertTrue;

/**
 * Created by user on 03/03/2017.
 */

public class TestChordBuilder {

    Pitch pitch;
    ChordBuilder chordBuilder;

    Parser regexParser = new RegexParser();

    @Before
    public void setup(){
        pitch = new Pitch();
        chordBuilder = new ChordBuilder(new Pitch());

    }

    @Test
    public void testAskingForChordReturnsFourNumbers(){
        List<ChordModel> models = new RegexParser().getChordModelsFromStringInput("C");
        ChordToneModel ctm = chordBuilder.build(models.get(0));
        assertEquals(3, ctm.getRoot().length);
        assertEquals(3, ctm.getThird().length);
        assertEquals(3, ctm.getFifth().length);
        assertEquals(3, ctm.getTopNote().length);
    }

    @Test
    public void testAskingForMajorChordReturnsTheCorrectNumbers(){
        List<ChordModel> models = regexParser.getChordModelsFromStringInput("D");
        ChordToneModel chordTones = chordBuilder.build(models.get(0));
        assertEquals(66, chordTones.getThird()[1] );
    }

    @Test
    public void testPullsCorrectTonesForMinorChords(){
        List<ChordModel> models = regexParser.getChordModelsFromStringInput("Em");
        ChordToneModel tones = chordBuilder.build(models.get(0));
        assertEquals(67, tones.getThird()[1]);
    }

    @Test
    public void testReturnsDominantMidiNotes(){
        List<ChordModel> models = regexParser.getChordModelsFromStringInput("G7");
        ChordToneModel tones = chordBuilder.build(models.get(0));
        assertEquals(77, tones.getTopNote()[1]);
    }

}

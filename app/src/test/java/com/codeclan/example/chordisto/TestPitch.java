package com.codeclan.example.chordisto;

import com.codeclan.example.chordisto.model.Pitch;
import com.codeclan.example.chordisto.parser.Parser;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 04/03/2017.
 */

public class TestPitch {

    Parser parser;
    Pitch pitch;

    @Before
    public void setup() {
        parser = new Parser();
        pitch = new Pitch();
    }

    @Test
    public void testThereAreTwelveBassPitches() {
        assertEquals(12, pitch.getBassPitchNumbers().size());
    }

    @Test
    public void testThereAreTwentyFourMiddlePitches(){
        assertEquals(24, pitch.getMiddlePitchNumbers().size());
    }

    @Test
    public void testThereAreTwelveRootEnums(){
        assertEquals(12, pitch.getRoots().size());
    }
}

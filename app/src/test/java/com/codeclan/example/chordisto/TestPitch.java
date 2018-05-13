package com.codeclan.example.chordisto;

import com.codeclan.example.chordisto.util.Pitch;
import com.codeclan.example.chordisto.parser.RegexParser;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 04/03/2017.
 */

public class TestPitch {

    RegexParser parser;
    Pitch pitch;

    @Before
    public void setup() {
        parser = new RegexParser();
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

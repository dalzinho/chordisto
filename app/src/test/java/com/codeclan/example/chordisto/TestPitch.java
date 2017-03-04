package com.codeclan.example.chordisto;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 04/03/2017.
 */

public class TestPitch {

    @Before
    Parser parser = new Parser();
    Pitch pitch = new Pitch();

    @Test
    public void testThereAreElevenBassPitches(){
        assertEquals(11, pitch.getBassPitches().length());
    }
}

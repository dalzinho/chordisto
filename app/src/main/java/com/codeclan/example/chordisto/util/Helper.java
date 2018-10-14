package com.codeclan.example.chordisto.util;

public class Helper {

    private static final int ONE_MINUTE = 60000;

    public static int calculateTempo(String tempoInput) {
        int tempo = Integer.parseInt(tempoInput);
        return ONE_MINUTE / tempo;
    }
}

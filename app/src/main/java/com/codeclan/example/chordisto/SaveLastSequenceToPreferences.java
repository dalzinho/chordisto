package com.codeclan.example.chordisto;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by user on 06/03/2017.
 */

public class SaveLastSequenceToPreferences {

    private static final String SAVED_SEQUENCE = "saved_sequence";
    private static final String SAVED_TEMPO = "saved_tempo";

    public static void setStoredSequence(Context context, String chords, int tempo){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(SAVED_SEQUENCE, chords)
                .apply();
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(SAVED_TEMPO, tempo)
                .apply();
    }

    public static String getStoredSequence(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(SAVED_SEQUENCE, null);
    }

    public static int getStoredTempo(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(SAVED_TEMPO, 0);
    }

}

package com.codeclan.example.chordisto;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by user on 06/03/2017.
 */

public class SaveLastSequenceToPreferences {

    private static final String SAVED_SEQUENCE = "saved_sequence";

    public static void setStoredSequence(Context context, String string){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(SAVED_SEQUENCE, string)
                .apply();
    }

    public static String getStoredSequence(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(SAVED_SEQUENCE, null);
    }

}

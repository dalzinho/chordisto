package com.codeclan.example.chordisto;

import android.content.Context;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiInputPort;
import android.media.midi.MidiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MidiActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MidiManager m = (MidiManager) this.getSystemService(Context.MIDI_SERVICE);
        MidiDeviceInfo[] infos = m.getDevices();
        MidiDeviceInfo info = infos[0];

        m.openDevice(info, new MidiManager.OnDeviceOpenedListener() {
                    @Override
                    public void onDeviceOpened(MidiDevice device) {
                        if (device == null) {
                            Log.e("Thing", "Thingy");
                        } else {
                            String arse;
                        }
                    }
                }, new Handler(Looper.getMainLooper())
        );

    }
}
package com.codeclan.example.chordisto.model;

import com.codeclan.example.chordisto.parser.RootName;
import com.codeclan.example.chordisto.parser.TriadType;

public class ChordInfo {

    private Pitch pitch;
    private TriadType triadType;
    private RootName rootName;
    private String theRest;

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public TriadType getTriadType() {
        return triadType;
    }

    public void setTriadType(TriadType triadType) {
        this.triadType = triadType;
    }

    public RootName getRootName() {
        return rootName;
    }

    public void setRootName(RootName rootName) {
        this.rootName = rootName;
    }

    public String getTheRest() {
        return theRest;
    }

    public void setTheRest(String theRest) {
        this.theRest = theRest;
    }
}

package com.codeclan.example.chordisto.model;

import com.codeclan.example.chordisto.chordenums.RootName;
import com.codeclan.example.chordisto.chordenums.TriadType;

/**
 * Created by john on 08/05/18.
 */

public class ChordModel {
    private RootName root;
    private TriadType type;

    public RootName getRoot() {
        return root;
    }

    public void setRoot(RootName root) {
        this.root = root;
    }

    public TriadType getType() {
        return type;
    }

    public void setType(TriadType type) {
        this.type = type;
    }
}

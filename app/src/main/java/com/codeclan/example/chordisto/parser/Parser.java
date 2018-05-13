package com.codeclan.example.chordisto.parser;

import com.codeclan.example.chordisto.model.ChordModel;

import java.util.List;

import dagger.Component;

/**
 * Created by john on 11/05/18.
 */

public interface Parser {

    public List<ChordModel> getChordModelsFromStringInput(String s);
}

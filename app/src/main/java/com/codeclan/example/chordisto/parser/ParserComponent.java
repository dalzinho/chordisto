package com.codeclan.example.chordisto.parser;

import com.codeclan.example.chordisto.app.MainActivity;

import dagger.Component;

/**
 * Created by john on 13/05/18.
 */
@Component(modules = {ParserModule.class})
public interface ParserComponent {

    void inject(MainActivity ma);
}

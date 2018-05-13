package com.codeclan.example.chordisto.parser;

import dagger.Module;
import dagger.Provides;

/**
 * Created by john on 13/05/18.
 */

@Module
class ParserModule {

    @Provides
    RegexParser getNewRegexParser() {
        return new RegexParser();
    }
}

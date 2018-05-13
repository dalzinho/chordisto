package com.codeclan.example.chordisto.util;

import com.codeclan.example.chordisto.chordenums.RootName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 03/03/2017.
 */

public class Pitch {

    private static final int LOWEST_BASS = 48;
    private static final int HIGHEST_BASS = 59;
    private static final int LOWEST_MIDDLE = 60;
    private static final int HIGHEST_MIDDLE = 83;
    private List<Integer> bassPitchNumbers;
    private List<Integer> middlePitchNumbers;
    private List<RootName> roots;

    public Pitch() {
        bassPitchNumbers = new ArrayList<>();
        int start = LOWEST_BASS;
        int last = HIGHEST_BASS;

        int counter = start;
        do {
            bassPitchNumbers.add(counter);
            counter++;
        } while (counter <= last);

        middlePitchNumbers = new ArrayList<>();
        start = LOWEST_MIDDLE;
        last = HIGHEST_MIDDLE;

        counter = start;

        do {
            middlePitchNumbers.add(counter);
            counter++;
        } while (counter <= last);

        roots = new ArrayList<>();

        for (RootName root : RootName.values()){
            roots.add(root);
        }

    }

    //some getters

    public List<Integer> getBassPitchNumbers() {
        return bassPitchNumbers;
    }

    public List<Integer> getMiddlePitchNumbers() {
        return middlePitchNumbers;
    }

    public List<RootName> getRoots() {
        return roots;
    }

    public int getRootIndexByName(RootName root){
        return roots.indexOf(root);
    }

    public int getBassValue(RootName root){
        return bassPitchNumbers.get(getRootIndexByName(root));
    }

    public int getThirdOfMiddleRegister(RootName root){
        return middlePitchNumbers.get(getRootIndexByName(root) + 4);
    }
}
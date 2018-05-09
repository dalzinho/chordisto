package com.codeclan.example.chordisto.util;

import com.codeclan.example.chordisto.chordenums.RootName;

import java.util.ArrayList;

/**
 * Created by user on 03/03/2017.
 */

public class Pitch {

    ArrayList<Integer> bassPitchNumbers;
    ArrayList<Integer> middlePitchNumbers;
    ArrayList<RootName> roots;

    public Pitch() {

        bassPitchNumbers = new ArrayList<>();
        //these ints are midi note names
        int start = 48;
        int last = 59;

        int counter = start;
        do {
            bassPitchNumbers.add(counter);
            counter++;
        } while (counter <= last);

        middlePitchNumbers = new ArrayList<>();
        start = 60;
        last = 83;

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

    public ArrayList<Integer> getBassPitchNumbers() {
        return bassPitchNumbers;
    }

    public ArrayList<Integer> getMiddlePitchNumbers() {
        return middlePitchNumbers;
    }

    public ArrayList<RootName> getRoots() {
        return roots;
    }

    public int getBassPitchByIndex(int index){
        return bassPitchNumbers.get(index);
    }

    public int getRootIndexByName(RootName root){
        return roots.indexOf(root);
    }

    public int getBassValue(RootName root){
        return bassPitchNumbers.get(getRootIndexByName(root));
    }

    public int getMiddlePitchByIndex(int index){
        return middlePitchNumbers.get(index);
    }

    public int getThirdOfMiddleRegister(RootName root){
        return middlePitchNumbers.get(getRootIndexByName(root) + 4);
    }
}
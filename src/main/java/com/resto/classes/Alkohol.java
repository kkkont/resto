package com.resto.classes;

import java.util.Scanner;

public class Alkohol extends Menu {
    private boolean kasOnAlkohol;


    public Alkohol(String roog, String tähis, double hind, boolean kasOnAlkohol) {
        super(roog, tähis, hind);
        this.kasOnAlkohol = kasOnAlkohol;
    }

    @Override
    public boolean kasOnAlkohol() {
        return true;
    }

    public String toString() {
        return super.toString() + "(18+)";
    }

}


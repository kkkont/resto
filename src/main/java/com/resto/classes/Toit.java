package com.resto.classes;

public class Toit extends Menu {
    public Toit(String roog, String tähis, double hind) {
        super(roog, tähis, hind);
    }

    @Override
    public boolean kasOnAlkohol() {
        return false;
    }

}

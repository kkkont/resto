package com.example.grupitoo;

public class Toit extends Menu {
    public Toit(String roog, String tähis, double hind) {
        super(roog, tähis, hind);
    }

    @Override
    boolean kasOnAlkohol() {
        return false;
    }

}

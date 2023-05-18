package com.example.grupitoo;

public abstract class Menu {
    private String roog;
    private String tähis;
    private double hind;

    public Menu(String roog, String tähis, double hind) {
        this.roog = roog;
        this.tähis = tähis;
        this.hind = hind;
    }

    abstract boolean kasOnAlkohol();

    public double getHind() {
        return hind;
    }

    public String getTähis() {
        return tähis;
    }

    public String getRoog() {
        return roog;
    }

    @Override
    public String toString() {
        return roog + " ".repeat(20 - roog.length() - 3) + hind;
    }
}

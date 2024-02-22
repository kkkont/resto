package com.resto.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tellimus {
    private double tellimuseMaksumus;
    private List<Menu> tellitudToidud;

    public Tellimus() {
        tellimuseMaksumus = 0;
        tellitudToidud = new ArrayList<>();
    }

    public void lisaRoog(Menu roog){
        tellitudToidud.add(roog);
    }
    public void võtabArve(){
        for (Menu roog : tellitudToidud) {
            tellimuseMaksumus += roog.getHind();
        }
    }
    public String tšekk(){
        String tulemus = "";
        for (Menu roog : tellitudToidud) {
            tulemus += roog.toString() + "\n";
        }
        return  "TŠEKK: \n" + tulemus +"\nSumma: "+ tellimuseMaksumus + "€";
    }

    @Override
    public String toString() {
        return tšekk();
    }
}

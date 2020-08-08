package com.hakanbey.nothesaplama.DataClass;

import java.util.Date;
import java.util.UUID;

public class Hatirlatmalar {

    private String baslik,id;
    private int gün,ay,yil,saat,dakika;

    public Hatirlatmalar() {
    }

    public String getId() {
        return id;
    }

    public Hatirlatmalar(String baslik, int gün, int ay, int yil, int saat, int dakika) {
        this.baslik = baslik;
        this.gün = gün;
        this.ay = ay;
        this.yil = yil;
        this.saat = saat;
        this.dakika = dakika;
        id= UUID.randomUUID().toString();
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public int getGün() {
        return gün;
    }

    public void setGün(int gün) {
        this.gün = gün;
    }

    public int getAy() {
        return ay;
    }

    public void setAy(int ay) {
        this.ay = ay;
    }

    public int getYil() {
        return yil;
    }

    public void setYil(int yil) {
        this.yil = yil;
    }

    public int getSaat() {
        return saat;
    }

    public void setSaat(int saat) {
        this.saat = saat;
    }

    public int getDakika() {
        return dakika;
    }

    public void setDakika(int dakika) {
        this.dakika = dakika;
    }
}

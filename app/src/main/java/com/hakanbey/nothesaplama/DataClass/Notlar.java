package com.hakanbey.nothesaplama.DataClass;

import java.util.UUID;

public class Notlar {

    private String baslik,not,id;

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getNot() {
        return not;
    }

    public void setNot(String not) {
        this.not = not;
    }

    public String getId() {
        return id;
    }

    public Notlar(String baslik, String not) {
        this.baslik = baslik;
        this.not = not;
        id= UUID.randomUUID().toString();
    }

    public Notlar() {
    }
}

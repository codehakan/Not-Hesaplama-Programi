package com.hakanbey.nothesaplama.DataClass;

public class KullaniciFactory {

    public static Kullanici createKullanici(Class sinif) throws InstantiationException, IllegalAccessException {
        return (Kullanici) sinif.newInstance();
    }
}

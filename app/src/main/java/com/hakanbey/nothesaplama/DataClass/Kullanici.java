package com.hakanbey.nothesaplama.DataClass;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class Kullanici {

    private String kullaniciadi, sifre, email, ogrencilikturu;

    public String getKullaniciadi() {
        return kullaniciadi;
    }

    public void setKullaniciadi(String kullaniciadi) {
        this.kullaniciadi = kullaniciadi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOgrencilikturu() {
        return ogrencilikturu;
    }

    public void setOgrencilikturu(String ogrencilikturu) {
        this.ogrencilikturu = ogrencilikturu;
    }

    public Kullanici() {
    }

    public Kullanici(String kullaniciadi, String sifre, String email, String ogrencilikturu) {
        this.kullaniciadi = kullaniciadi;
        this.sifre = sifre;
        this.email = email;
        this.ogrencilikturu = ogrencilikturu;
    }

    //public abstract void bilgileriCek();

}

package com.hakanbey.nothesaplama.DataClass;

import android.database.Cursor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hakanbey.nothesaplama.*;
import com.hakanbey.nothesaplama.Auth.RegisterActivity;

public class UniversiteOgrencisi extends Kullanici{

    private String universiteadi="Henüz Üniversite Adı Girmediniz";

    public String getUniversiteadi() {
        return universiteadi;
    }

    public void setUniversiteadi(String universiteadi) {
        this.universiteadi = universiteadi;
    }

    public UniversiteOgrencisi(String kullaniciadi, String sifre, String email, String ogrencilikturu) {
        this.setKullaniciadi(kullaniciadi);
        this.setEmail(email);
        this.setSifre(sifre);
        this.setOgrencilikturu(ogrencilikturu);
    }

    public UniversiteOgrencisi(){

    }

}

package com.hakanbey.nothesaplama.DataClass;

import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hakanbey.nothesaplama.Auth.RegisterActivity;

public class LiseOgrencisi extends Kullanici {

    private String liseadi="Henüz Lise Adı Girmediniz";

    public String getLiseadi() {
        return liseadi;
    }

    public void setLiseadi(String liseadi) {
        this.liseadi = liseadi;
    }

    public LiseOgrencisi() {

    }

    public LiseOgrencisi(String kullaniciadi, String sifre, String email, String ogrencilikturu) {
        this.setKullaniciadi(kullaniciadi);
        this.setEmail(email);
        this.setSifre(sifre);
        this.setOgrencilikturu(ogrencilikturu);
    }

}

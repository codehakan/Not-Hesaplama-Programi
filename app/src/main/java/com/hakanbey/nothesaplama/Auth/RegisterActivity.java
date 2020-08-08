package com.hakanbey.nothesaplama.Auth;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hakanbey.nothesaplama.DataClass.Kullanici;
import com.hakanbey.nothesaplama.DataClass.KullaniciFactory;
import com.hakanbey.nothesaplama.DataClass.LiseOgrencisi;
import com.hakanbey.nothesaplama.DataClass.UniversiteOgrencisi;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

public class RegisterActivity extends AppCompatActivity {
    public static UniversiteOgrencisi universite;
    public static LiseOgrencisi lise;
    private EditText email, sifre, sifretekrar, kullaniciadi;
    private Button kayitol, girisyap;
    private Switch ogrencilikturu;

    /*
    private FirebaseAuth auth;
    private FirebaseDatabase db;*/

    public void init() {
        kullaniciadi = (EditText) findViewById(R.id.txtbx_kullaniciadi);
        sifre = (EditText) findViewById(R.id.txtbx_sifre);
        sifretekrar = (EditText) findViewById(R.id.txtbx_sifreretry);
        email = (EditText) findViewById(R.id.txtbx_email);
        kayitol = (Button) findViewById(R.id.bttn_kayitol);
        girisyap = (Button) findViewById(R.id.bttn_girisyap);
        ogrencilikturu=(Switch) findViewById(R.id.swicthogrenci);
        /*
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();*/
    }

    private void kullaniciKaydi() throws IllegalAccessException, InstantiationException {
        String epost = email.getText().toString();
        String username = kullaniciadi.getText().toString();
        String password = sifre.getText().toString();
        String passwordretry = sifretekrar.getText().toString();

        if (TextUtils.isEmpty(epost) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Alanları Boş Bırakmadığınızdan Emin Olun !", Toast.LENGTH_LONG);
        } else {
            if (TextUtils.equals(password, passwordretry)) {
                if (password.length() >= 6) {
                    if(ogrencilikturu.isChecked()){ //üniversite

                        //factory çağrılıyor
                        universite=(UniversiteOgrencisi) KullaniciFactory.createKullanici(UniversiteOgrencisi.class);
                        universite.setKullaniciadi(username);
                        universite.setSifre(password);
                        universite.setEmail(epost);
                        universite.setOgrencilikturu("Üniversite");
                        //factory çağrılıyor

                        //universite = new UniversiteOgrencisi(username, password, epost,"Üniversite");
                        MainActivity.vb.getAuth().createUserWithEmailAndPassword(epost, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    MainActivity.vb.getDbref("Users/"+MainActivity.vb.getUserID()).setValue(universite);
                                    Toast.makeText(RegisterActivity.this, "Hesabınız Başarılı Bir Şekilde Oluşturuldu", Toast.LENGTH_SHORT).show();
                                    Intent goLoginInt = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(goLoginInt);
                                    finish();
                                    kayitol.setEnabled(true);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Sunucuyla Bağlantı Sağlanamadı ! Hesap Oluşturulamadı", Toast.LENGTH_SHORT).show();
                                    kayitol.setEnabled(true);
                                }
                            }
                        });
                    }else{//Lise

                        //factory çağrılıyor
                        lise=(LiseOgrencisi) KullaniciFactory.createKullanici(LiseOgrencisi.class);
                        lise.setKullaniciadi(username);
                        lise.setSifre(password);
                        lise.setEmail(epost);
                        lise.setOgrencilikturu("Lise");
                        //factory çağrılıyor

                        //lise = new LiseOgrencisi(username, password, epost,"Lise");
                        MainActivity.vb.getAuth().createUserWithEmailAndPassword(epost, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    MainActivity.vb.getDbref("Users/"+MainActivity.vb.getUserID()).setValue(lise);
                                    Toast.makeText(RegisterActivity.this, "Hesabınız Başarılı Bir Şekilde Oluşturuldu", Toast.LENGTH_SHORT).show();
                                    Intent goLoginInt = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(goLoginInt);
                                    finish();
                                    kayitol.setEnabled(true);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Sunucuyla Bağlantı Sağlanamadı ! Hesap Oluşturulamadı", Toast.LENGTH_SHORT).show();
                                    kayitol.setEnabled(true);
                                }
                            }
                        });
                    }
                } else {
                    Toast.makeText(this, "Lütfen 6 Haneden Büyük Şifre Giriniz !", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Şifrenizi Doğru Tekrar Ettiğinizden Emin Olun !", Toast.LENGTH_LONG);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kayitol.setEnabled(false);
                try {
                    kullaniciKaydi();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        });

        girisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girisyap.setEnabled(false);
                Intent goLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(goLogin);
                finish();
                girisyap.setEnabled(true);
            }
        });


    }
}

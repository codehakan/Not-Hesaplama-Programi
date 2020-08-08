package com.hakanbey.nothesaplama.Universite;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hakanbey.nothesaplama.DataClass.UniversiteHesaplamalar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

public class vizefinalhesaplama extends AppCompatActivity {

    TextView lbl_ortalamaAyari, lbl_sonuc, lbl_finalSiniri;
    EditText txt_vize, txt_final, txt_vizeoraninigoster, txt_finaloraninigoster;
    Button btn_hesapla;

    private static final String TAG = "MainActivity";
    int vizeAyari, finalAyari, ortalamaAyari, finalSiniri;

    private String VIZE_KEY = "com.hakanbey.nothesaplama.VIZE";
    private String FINAL_KEY = "com.hakanbey.nothesaplama.FINAL";
    private String FINAL_SINIRI = "com.hakanbey.nothesaplama.FINAL_SINIRI";
    private String ORTALAMA_KEY = "com.hakanbey.nothesaplama.ORTALAMA";
    private String MAIN_KEY = "com.hakanbey.nothesaplama.MAIN_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizefinalhesaplama);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // klavye açık gelmesin
        /*
        çalışmadı.
         */
        // klavye açık gelmesin

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vizefinalhesaplama.this, ayarlar.class);
                startActivity(intent);
            }
        });

        vizeAyari = getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getInt(VIZE_KEY, -1);
        finalAyari = getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getInt(FINAL_KEY, -1);
        finalSiniri = getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getInt(FINAL_SINIRI, -1);
        ortalamaAyari = getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getInt(ORTALAMA_KEY, -1);

        lbl_ortalamaAyari = (TextView) findViewById(R.id.lbl_ortalamaAyari);
        lbl_sonuc = (TextView) findViewById(R.id.lbl_sonuc);
        lbl_finalSiniri = (TextView) findViewById(R.id.lbl_finalSiniri);

        txt_vize = (EditText) findViewById(R.id.txt_vize);
        txt_final = (EditText) findViewById(R.id.txt_final);
        btn_hesapla = (Button) findViewById(R.id.btn_hesaplavizefinal);
        txt_vizeoraninigoster = (EditText) findViewById(R.id.txt_vizeninoraninigoster);
        txt_finaloraninigoster = (EditText) findViewById(R.id.txt_finaloraninigoster);


        txt_vizeoraninigoster.setText("" + vizeAyari);
        txt_vizeoraninigoster.setEnabled(false);
        txt_finaloraninigoster.setText("" + finalAyari);
        txt_finaloraninigoster.setEnabled(false);
        lbl_finalSiniri.setText("Final Sınırı: " + finalSiniri);
        lbl_ortalamaAyari.setText("Ortalama Sınırı: " + ortalamaAyari);


        if (vizeAyari == (-1) || finalAyari == (-1) || ortalamaAyari == (-1) || finalSiniri == (-1)) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Sağ altta bulunan ayarlar simgesine tıklayarak kendi üniversitenize ait hesaplama ayarlarını kayıt ediniz. Aksi halde doğru sonuç alamazsınız.");
            dlgAlert.setTitle("Not Hesaplama Ayarları");
            dlgAlert.setPositiveButton("Tamam, teşekkürler.", null);
            dlgAlert.setCancelable(false);
            dlgAlert.create().show();
            btn_hesapla.setEnabled(false);
        } else {
            btn_hesapla.setEnabled(true);
        }


        btn_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_vize.getText().toString().trim().equals("") || txt_final.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Lütfen not girişlerini eksiksiz yapın.", Toast.LENGTH_LONG).show();
                    lbl_sonuc.setText("--");
                } else {
                    if (Float.parseFloat(txt_vize.getText().toString()) > 100) {
                        Toast.makeText(getApplicationContext(), "Vize notu 100'den yüksek olamaz.", Toast.LENGTH_LONG).show();
                        lbl_sonuc.setText("--");
                    } else if (Float.parseFloat(txt_final.getText().toString()) > 100) {
                        Toast.makeText(getApplicationContext(), "Final notu 100'den yüksek olamaz.", Toast.LENGTH_LONG).show();
                        lbl_sonuc.setText("--");
                    } else {
                        float vizeNotu = Float.parseFloat(txt_vize.getText().toString());
                        float finalNotu = Float.parseFloat(txt_final.getText().toString());
                        float ortalamaSonucu = (vizeNotu * vizeAyari / 100) + (finalNotu * finalAyari / 100);
                        UniversiteHesaplamalar yenihesap=new UniversiteHesaplamalar(vizeNotu,finalNotu,ortalamaSonucu);
                        //singleton veritabanı bağlantısı
                        MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);
                        Toast.makeText(vizefinalhesaplama.this, "Hesaplama yapıldı", Toast.LENGTH_LONG).show();
                        //singleton veritabanı bağlantısı
                        if (finalNotu < finalSiniri) {
                            lbl_sonuc.setBackgroundColor(Color.parseColor("#ef0909"));
                            lbl_sonuc.setText(String.valueOf(ortalamaSonucu));
                            Typeface tf = Typeface.defaultFromStyle((Typeface.BOLD));
                            lbl_sonuc.setTypeface(tf);
                        } else if (ortalamaSonucu < ortalamaAyari) {
                            lbl_sonuc.setBackgroundColor(Color.parseColor("#ef0909"));
                            lbl_sonuc.setText(String.valueOf(ortalamaSonucu));
                            Typeface tf = Typeface.defaultFromStyle((Typeface.BOLD));
                            lbl_sonuc.setTypeface(tf);
                        } else {
                            lbl_sonuc.setBackgroundColor(Color.parseColor("#1cef09"));
                            lbl_sonuc.setText(String.valueOf(ortalamaSonucu));
                            Typeface tf = Typeface.defaultFromStyle((Typeface.BOLD));
                            lbl_sonuc.setTypeface(tf);
                        }

                    }

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

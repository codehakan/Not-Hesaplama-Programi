package com.hakanbey.nothesaplama.Universite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

public class ayarlar extends AppCompatActivity {


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String VIZE_KEY = "com.hakanbey.nothesaplama.VIZE";
    private String FINAL_KEY = "com.hakanbey.nothesaplama.FINAL";
    private String FINAL_SINIRI = "com.hakanbey.nothesaplama.FINAL_SINIRI";
    private String ORTALAMA_KEY = "com.hakanbey.nothesaplama.ORTALAMA";
    private String MAIN_KEY = "com.hakanbey.nothesaplama.MAIN_DATA";

    EditText txt_vizeAyari, txt_finalAyari, txt_ortalamaAyari, txt_finalSiniri;
    Button btn_kaydet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txt_vizeAyari = (EditText) findViewById(R.id.txt_vizeAyari);
        txt_finalAyari = (EditText) findViewById(R.id.txt_finalAyari);
        txt_ortalamaAyari = (EditText) findViewById(R.id.txt_ortalamaAyari);
        txt_finalSiniri = (EditText) findViewById(R.id.txt_finalSiniri);

        btn_kaydet = (Button) findViewById(R.id.btn_kaydet);


        btn_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_vizeAyari.getText().toString().trim().equals("") || txt_finalAyari.getText().toString().trim().equals("") || txt_ortalamaAyari.getText().toString().trim().equals("")) {
                    Toast.makeText(ayarlar.this, "Lütfen boş değer bırakmayınız.", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferences = getSharedPreferences(MAIN_KEY, MODE_PRIVATE);
                    editor = sharedPreferences.edit();

                    editor.putInt(VIZE_KEY, Integer.parseInt(txt_vizeAyari.getText().toString()));
                    editor.putInt(FINAL_KEY, Integer.parseInt(txt_finalAyari.getText().toString()));
                    editor.putInt(FINAL_SINIRI, Integer.parseInt(txt_finalSiniri.getText().toString()));
                    editor.putInt(ORTALAMA_KEY, Integer.parseInt(txt_ortalamaAyari.getText().toString()));
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Ayarlar kayıt edildi !", Toast.LENGTH_LONG).show();
                    Intent show = new Intent(ayarlar.this, vizefinalhesaplama.class);
                    startActivity(show);
                    finish();
                }
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }

        //noinspection SimplifiableIfStatement
        /*if (id == R.layout.activity_vizefinalhesaplama) {
            //this.finish();
            super.onBackPressed();
            //Intent show = new Intent(this, MainActivity.class);
            //NavUtils.navigateUpTo(this,show);
            //startActivity(show);
            //Toast.makeText(this, "Ayarları lütfen dikkatlice yapın.", Toast.LENGTH_SHORT).show();
            return true;
        }*/

        return super.onOptionsItemSelected(item);

    }

}

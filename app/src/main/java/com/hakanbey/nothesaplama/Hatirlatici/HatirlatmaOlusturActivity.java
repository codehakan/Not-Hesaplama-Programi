package com.hakanbey.nothesaplama.Hatirlatici;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.hakanbey.nothesaplama.DataClass.Hatirlatmalar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

import java.text.DateFormat;
import java.util.Calendar;

public class HatirlatmaOlusturActivity extends AppCompatActivity {

    private EditText hatirlatmabaslik;
    private Button hatirlatmazamanbtn,kaydet;
    int gün,ay,yil,saat,dakika;

    public void init(){
        hatirlatmabaslik=findViewById(R.id.hatirlatmabaslikyeri);
        hatirlatmazamanbtn=findViewById(R.id.hatirlatmazamanbtn);
        kaydet=findViewById(R.id.hatirlatmakaydet);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatirlatma_olustur);

        init();

        hatirlatmazamanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                yil=c.get(Calendar.YEAR);
                ay=c.get(Calendar.MONTH);
                gün=c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(HatirlatmaOlusturActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        yil=year;
                        ay=month+1;
                        gün=dayOfMonth;

                        Calendar calendar=Calendar.getInstance();
                        saat=calendar.get(Calendar.HOUR);
                        dakika=calendar.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog=new TimePickerDialog(HatirlatmaOlusturActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                saat=hourOfDay;
                                dakika=minute;
                                
                                hatirlatmazamanbtn.setText(saat+":"+dakika+" "+gün+"/"+ay+"/"+yil);
                            }
                        },saat,dakika,true);
                        timePickerDialog.show();
                    }
                },yil,ay,gün);
                datePickerDialog.show();
            }
        });
        
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baslik=hatirlatmabaslik.getText().toString();
                Hatirlatmalar yenihatirlatma=new Hatirlatmalar(baslik,gün,ay,yil,saat,dakika);
                MainActivity.vb.getDbref("Hatirlatmalar")
                        .child(MainActivity.vb.getUserID())
                        .push()
                        .setValue(yenihatirlatma)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent go=new Intent(HatirlatmaOlusturActivity.this,HatirlaticilarActivity.class);
                        startActivity(go);
                        finish();
                        Toast.makeText(HatirlatmaOlusturActivity.this, "Hatırlatma Eklendi.", Toast.LENGTH_SHORT).show();     
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HatirlatmaOlusturActivity.this, "Hatırlatma eklenirken bir hata oluştu !", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

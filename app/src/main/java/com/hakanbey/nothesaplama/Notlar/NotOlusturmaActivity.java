package com.hakanbey.nothesaplama.Notlar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.hakanbey.nothesaplama.DataClass.Notlar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

public class NotOlusturmaActivity extends AppCompatActivity {

    private EditText notbaslikyeri,notyazmayeri;
    private Button notolusturmabtn;
    private String baslik, not;
    private Notlar yeninot;

    public void init(){
        notbaslikyeri=(EditText) findViewById(R.id.notbaslikyeri);
        notyazmayeri=(EditText) findViewById(R.id.notyazmayeri);
        notolusturmabtn=(Button) findViewById(R.id.notolustur);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_olusturma);

        init();

        notolusturmabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baslik=notbaslikyeri.getText().toString();
                not=notyazmayeri.getText().toString();

                yeninot=new Notlar(baslik,not);

                MainActivity.vb.getVeritabani().getDbref("Notlar/").child(MainActivity.vb.getUserID()).push().setValue(yeninot).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(NotOlusturmaActivity.this, "Not Oluşturuldu", Toast.LENGTH_LONG).show();

                        Intent goNoteList=new Intent(NotOlusturmaActivity.this,NotlarActivity.class);
                        startActivity(goNoteList);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NotOlusturmaActivity.this, "Not Oluşturulamadı !", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}

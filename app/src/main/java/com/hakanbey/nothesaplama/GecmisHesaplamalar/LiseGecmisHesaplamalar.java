package com.hakanbey.nothesaplama.GecmisHesaplamalar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.hakanbey.nothesaplama.Adapter.LiseAdapter;
import com.hakanbey.nothesaplama.DataClass.LiseHesaplamalar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LiseGecmisHesaplamalar extends AppCompatActivity {
    // Android layout
    ListView liste;

    // Array List
    public List<LiseHesaplamalar> hesaplamalar = new ArrayList<LiseHesaplamalar>();
    private LiseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lise_gecmis_hesaplamalar);

        liste = (ListView) findViewById(R.id.LiseGecmisi);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MainActivity.vb.getDbref("GecmisHesaplamalar")
                .child(MainActivity.vb.getUserID())
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LiseHesaplamalar user = snapshot.getValue(LiseHesaplamalar.class);
                    hesaplamalar.add(user);
                    Collections.reverse(hesaplamalar);
                    liste.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter = new LiseAdapter(this, hesaplamalar);
        if (liste != null) {
            liste.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

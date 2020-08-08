package com.hakanbey.nothesaplama.GecmisHesaplamalar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hakanbey.nothesaplama.Adapter.UniversiteAdapter;
import com.hakanbey.nothesaplama.DataClass.UniversiteHesaplamalar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UniversiteGecmisHesaplamalar extends AppCompatActivity {
    // Android layout
    ListView liste;

    // Array List
    public List<UniversiteHesaplamalar> hesaplamalar = new ArrayList<UniversiteHesaplamalar>();
    private UniversiteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universite_gecmis_hesaplar);


        liste = (ListView) findViewById(R.id.universiteGecmisi);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        MainActivity.vb.getDbref("GecmisHesaplamalar")
                .child(MainActivity.vb.getUserID())
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UniversiteHesaplamalar user = snapshot.getValue(UniversiteHesaplamalar.class);
                    hesaplamalar.add(user);
                    Collections.reverse(hesaplamalar);
                    liste.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter = new UniversiteAdapter(this, hesaplamalar);
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

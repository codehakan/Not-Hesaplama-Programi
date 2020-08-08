package com.hakanbey.nothesaplama.Notlar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.hakanbey.nothesaplama.Adapter.NotlarAdapter;
import com.hakanbey.nothesaplama.DataClass.Notlar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotlarActivity extends AppCompatActivity {

    private List<Notlar> notlarim = new ArrayList<Notlar>();
    private ListView listem;
    private NotlarAdapter adapter;

    private void notlariCek(){
        MainActivity.vb.getDbref("Notlar")
                .child(MainActivity.vb.getUserID())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        notlarim.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Notlar user = snapshot.getValue(Notlar.class);
                            System.out.println(user.toString());
                            notlarim.add(user);
                            listem.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        notlariCek();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notlar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabnotlar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goNote=new Intent(NotlarActivity.this,NotOlusturmaActivity.class);
                startActivity(goNote);
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listem = (ListView) findViewById(R.id.notlarimList);

        adapter = new NotlarAdapter(this, notlarim);
        if (listem != null) {
            listem.setAdapter(adapter);
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

package com.hakanbey.nothesaplama.Hatirlatici;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.hakanbey.nothesaplama.Adapter.HatirlatmaAdaptor;
import com.hakanbey.nothesaplama.DataClass.Hatirlatmalar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

import java.util.ArrayList;
import java.util.List;

public class HatirlaticilarActivity extends AppCompatActivity {

    private FloatingActionButton fabhatirlatmaolustur;
    private ListView hatirlatmalarimlistview;
    private List<Hatirlatmalar> hatirlatmalarim=new ArrayList<Hatirlatmalar>();
    private HatirlatmaAdaptor adapter;
    private Context c=this;

    private void hatirlatmalariCek(){
        MainActivity.vb.getDbref("Hatirlatmalar")
                .child(MainActivity.vb.getUserID())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        hatirlatmalarim.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Hatirlatmalar user = snapshot.getValue(Hatirlatmalar.class);
                            System.out.println(user.toString());
                            hatirlatmalarim.add(user);
                            //Collections.sort(hatirlatmalarim);
                            hatirlatmalarimlistview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void init(){
        fabhatirlatmaolustur=findViewById(R.id.fabhatirlatmaolustur);
        hatirlatmalarimlistview=findViewById(R.id.hatirlatmalarimlistview);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        adapter = new HatirlatmaAdaptor(hatirlatmalarim, this);
        if (hatirlatmalarimlistview != null) {
            hatirlatmalarimlistview.setAdapter(adapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        hatirlatmalariCek();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatirlaticilar);

        init();

        fabhatirlatmaolustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goIntent=new Intent(HatirlaticilarActivity.this,HatirlatmaOlusturActivity.class);
                startActivity(goIntent);
            }
        });
    }
}

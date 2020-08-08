package com.hakanbey.nothesaplama;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hakanbey.nothesaplama.Auth.LoginActivity;
import com.hakanbey.nothesaplama.Auth.WelcomeActivity;
import com.hakanbey.nothesaplama.GecmisHesaplamalar.LiseGecmisHesaplamalar;
import com.hakanbey.nothesaplama.GecmisHesaplamalar.UniversiteGecmisHesaplamalar;
import com.hakanbey.nothesaplama.Hatirlatici.HatirlaticilarActivity;
import com.hakanbey.nothesaplama.Lise.LiseHesaplamalariMain;
import com.hakanbey.nothesaplama.Notlar.NotlarActivity;
import com.hakanbey.nothesaplama.Universite.vizefinalhesaplama;
import com.hakanbey.nothesaplama.Veritabani.VeriTabani;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button baslat;
    private String tip;
    private NavigationView menu;

    //program boyunca sadece bir defa oluşturuldu
    public static VeriTabani vb = VeriTabani.getVeritabani();
    //program boyunca sadece bir defa oluşturuldu

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu=findViewById(R.id.nav_view);

        //aktif kullanıcı kontrolü
        if (MainActivity.vb.getCurrentuser() == null) {
            Intent goLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(goLogin);
            Toast.makeText(this, "kontrol etti", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            //kullanici tipinin belirlenmesi
            MainActivity.vb.getDbref("Users")
                    .child(MainActivity.vb.getUserID())
                    .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    tip = dataSnapshot.child("ogrencilikturu").getValue().toString();

                    Toast.makeText(MainActivity.this, tip, Toast.LENGTH_LONG).show();

                    if(tip.equals("Lise")){
                        menu.getMenu().getItem(2).setVisible(false);
                    }else{
                        menu.getMenu().getItem(1).setVisible(false);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            //kullanici tipinin belirlenmesi
        }
        //aktif kullanıcı kontrolü


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        baslat = (Button) findViewById(R.id.btn_hesaplamayaBasla);
        baslat.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.START);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.signout) {
            MainActivity.vb.getAuth().signOut();
            Intent goWelcome = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(goWelcome);
            finish();
        }

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            Intent show = new Intent(MainActivity.this, ayarlar.class);
            startActivity(show);
            Toast.makeText(this, "Ayarları lütfen dikkatlice yapın.", Toast.LENGTH_SHORT).show();
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.gecmis_hesaplamalar) {
            if (tip.equals("Üniversite")) {
                Intent intent = new Intent(MainActivity.this, UniversiteGecmisHesaplamalar.class);
                startActivity(intent);
            } else if (tip.equals("Lise")) {
                Intent intent = new Intent(MainActivity.this, LiseGecmisHesaplamalar.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Öğrenci tipi belirlenemedi.", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.lisehesaplama) {
            Intent intent = new Intent(MainActivity.this, LiseHesaplamalariMain.class);
            startActivity(intent);
        } else if (id == R.id.universitehesaplama) {
            Intent intent = new Intent(MainActivity.this, vizefinalhesaplama.class);
            startActivity(intent);

        } else if (id == R.id.notlarim) {
            Intent intent = new Intent(MainActivity.this, NotlarActivity.class);
            startActivity(intent);
        }else if(id == R.id.hatirlatmalaragit){
            Intent go=new Intent(MainActivity.this, HatirlaticilarActivity.class);
            startActivity(go);
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Bu uygulamayı kullanmak çok kolay ! Sana da tavsiye ediyorum. https://goo.gl/zXKrP4");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.nav_send) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"hakan_akkaya_11@hotmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Konu Başlığını Girin");
            i.putExtra(Intent.EXTRA_TEXT, "Mesajınızı girin.");
            try {
                startActivity(Intent.createChooser(i, "E-Posta Gönder"));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this, "E-Posta gönderimi sağlanamadı.", Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(this, "bakımda", Toast.LENGTH_SHORT).show();

        } /*else if (id == R.id.gecmis_hesaplamalar2) {
            Intent intent = new Intent(MainActivity.this, gecmishesaplamalarana.class);
            startActivity(intent);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

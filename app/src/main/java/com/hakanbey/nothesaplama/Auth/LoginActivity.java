package com.hakanbey.nothesaplama.Auth;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

public class LoginActivity extends AppCompatActivity {

    private Button girisyap2,kayitol2;
    private EditText txtbxsifre2,txtbxemail2;
    private Toolbar toolbarLogin;
    private FirebaseAuth auth;
    private FirebaseUser aktifkullanici;

    public void init(){
        girisyap2=(Button) findViewById(R.id.bttn_girisyap2);
        kayitol2=(Button) findViewById(R.id.bttn_kayitol2);
        txtbxemail2=(EditText) findViewById(R.id.txtbx_email2);
        txtbxsifre2=(EditText) findViewById(R.id.txtbx_sifre2);
        auth=FirebaseAuth.getInstance();
        aktifkullanici=auth.getCurrentUser();
    }

    private void girisYapFonk() {
        String epost2=txtbxemail2.getText().toString();
        String password2=txtbxsifre2.getText().toString();

        if(TextUtils.isEmpty(epost2) || TextUtils.isEmpty(password2)){
            Toast.makeText(this, "E-Mail ve Şifrenizi Giriniz !", Toast.LENGTH_LONG).show();
        }else{
            auth.signInWithEmailAndPassword(epost2,password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Giriş Başarılı", Toast.LENGTH_LONG).show();
                        Intent goMan=new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(goMan);
                        finish();
                        girisyap2.setEnabled(true);
                    }else{
                        Toast.makeText(LoginActivity.this, "Sunucuyla Bağlantı Kurulamadı !", Toast.LENGTH_SHORT).show();
                        girisyap2.setEnabled(true);
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        girisyap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girisyap2.setEnabled(false);
                girisYapFonk();
            }
        });

        kayitol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kayitol2.setEnabled(false);
                Intent goRegister= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(goRegister);
                finish();
                kayitol2.setEnabled(true);
            }
        });
    }
}

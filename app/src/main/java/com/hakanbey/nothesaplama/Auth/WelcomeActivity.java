package com.hakanbey.nothesaplama.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.hakanbey.nothesaplama.R;

public class WelcomeActivity extends AppCompatActivity {
    public static FirebaseUser currentUser;

    private Button login,register;

    public void init(){
        login=(Button) findViewById(R.id.btn_login);
        register=(Button) findViewById(R.id.btn_register);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goLogin=new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(goLogin);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRegister=new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(goRegister);
                finish();
            }
        });

    }
}

package com.savi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button btnSession;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnSession=(Button) findViewById(R.id.btnSession);
        contexto=getApplicationContext();

        addListeners();
    }

    public void addListeners(){
        btnSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent principal=new Intent(contexto, PrincipalActivity.class);
                startActivity(principal);
            }
        });
    }
}
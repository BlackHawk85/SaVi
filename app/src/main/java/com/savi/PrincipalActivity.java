package com.savi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PrincipalActivity extends AppCompatActivity {

    ImageButton iBtnBuscar;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        iBtnBuscar=(ImageButton) findViewById(R.id.iBtnBuscar);

        contexto=getApplicationContext();


        addListeners();

    }

    public void addListeners(){
        iBtnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buscar=new Intent(contexto, BuscarMedicoActivity.class);
                startActivity(buscar);
            }
        });
    }
}
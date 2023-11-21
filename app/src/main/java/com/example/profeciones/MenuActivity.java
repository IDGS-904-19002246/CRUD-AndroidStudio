package com.example.profeciones;

import androidx.appcompat.app.AppCompatActivity;

import  com.example.profeciones.databinding.ActivityMenuBinding;

import android.content.Intent;
import android.os.Bundle;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R  .layout.activity_menu);
        b = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.PRO.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        b.DBs.setOnClickListener(v -> {
            Intent intent = new Intent(this, DBsActivity.class);
            startActivity(intent);
        });
        b.DIA.setOnClickListener(v -> {
            Intent intent = new Intent(this, DiasActivity.class);
            startActivity(intent);
        });
    }
}
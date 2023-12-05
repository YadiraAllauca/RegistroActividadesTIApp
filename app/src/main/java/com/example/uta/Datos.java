package com.example.uta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Datos extends AppCompatActivity {

    EditText dt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        dt = findViewById(R.id.datos);
        dt.setEnabled(false);

        String aux = getIntent().getStringExtra("actividades");
        dt.setText(aux);
    }
}
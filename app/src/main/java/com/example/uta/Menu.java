package com.example.uta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    Button btnCambioC, btnRegistro, btnCargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnCambioC = (Button) findViewById(R.id.btnCambioContrasena);
        btnRegistro = (Button) findViewById(R.id.btnRegistroActividades);
        btnCargar = (Button) findViewById(R.id.btnConsultaActividades);

        accionesBotones();
    }

    private void accionesBotones(){
        btnCambioC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in1 = new Intent(getApplicationContext(), CambioContrasena.class);
                startActivity(in1);
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreU = getIntent().getStringExtra("nomUser");
                Intent in = new Intent(getApplicationContext(), RegistroActividad.class);
                in.putExtra("user",nombreU);
                startActivity(in);
            }
        });

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), ConsultaActividades.class);
                startActivity(in);
            }
        });
    }

    private void registrarActividad(){
        btnCambioC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), CambioContrasena.class);
                startActivity(in);
            }
        });
    }

    private void buscarActividad(){
        btnCambioC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), CambioContrasena.class);
                startActivity(in);
            }
        });
    }

}

package com.example.uta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    EditText user, contra;
    Button btnIniciar, btnregistrarUser;
    ImageButton btnVer;
    String aux1, aux2;
    int aux3=0;
    private int status2=0;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText) findViewById(R.id.txtUser);
        contra = (EditText) findViewById(R.id.txtContrasena);
        btnIniciar = (Button) findViewById(R.id.btnLogin);
        //btnregistrarUser = (Button) findViewById(R.id.btnRegistrarme);
        btnVer = (ImageButton) findViewById(R.id.btnVer);

        init();
    }

    public void logIn( String URL ){
        String nombre = user.getText().toString();
        String contrasena = contra.getText().toString();
        JsonArrayRequest jr = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject object = null;
                    for( int i=0; i<response.length(); i++ ){

                        object = response.getJSONObject(i);
                        aux1 = object.getString("NOM_USER") ;
                        aux2 = object.getString("CONT_USER");

                        Intent in = new Intent(getApplicationContext(), Menu.class);
                        in.putExtra("nomUser", user.getText().toString());
                        startActivity(in);
                        aux3++;
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "vacio", Toast.LENGTH_SHORT).show();
                }
                if( aux3 == 0  || aux3 < 1 ){
                    Toast.makeText(getApplicationContext(), "usuario o contraseña incorrectas", Toast.LENGTH_SHORT).show();
                    aux3=0;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue = Volley.newRequestQueue(this);
        queue.add(jr);
    }

    private void init(){
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( user.getText().toString().isEmpty() && contra.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Complete los campos ", Toast.LENGTH_SHORT).show();
                }else {
                    logIn("https://actividadesuta.000webhostapp.com/buscarUsuario.php?NOM_USER="+user.getText().toString()+"&CONT_USER="+contra.getText().toString());
                }
            }
        });

        //registrarse
        /*btnregistrarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), RegistrarUsario.class);
                startActivity(in);
            }
        });*/

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( status2==0 ){
                    contra.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    status2=1;
                }else{
                    contra.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    status2=0;
                }
            }
        });
    }

}
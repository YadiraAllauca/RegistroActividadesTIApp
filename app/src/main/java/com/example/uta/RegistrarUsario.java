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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegistrarUsario extends AppCompatActivity {

    EditText nombre, contrasena, direccion;
    Button btnReg;
    ImageButton btnV;
    RequestQueue queue;
    String aux1, aux2;
    int aux3=0;
    private int status2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usario);
        nombre=findViewById(R.id.txtUsers);
        contrasena=findViewById(R.id.txtContrasenas);
        direccion =findViewById(R.id.txtDireccion);
        btnReg=findViewById(R.id.btnRegistro);
        btnV=(ImageButton)findViewById(R.id.btnView);
        accionBoton();
    }

    private void insertarUser(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Insertado", Toast.LENGTH_SHORT).show();
                limpiar();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("NOM_USER", nombre.getText().toString());
                parametros.put("CONT_USER", contrasena.getText().toString());
                parametros.put("DIR_USER", direccion.getText().toString());

                return parametros;
            }
        };
        queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void accionBoton(){
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insertarUser("http://192.168.0.14/UTA/registrarUsuario.php");
                buscarUsuario("https://actividadesuta.000webhostapp.com/buscarUsuario.php?NOM_USER="+nombre.getText().toString()+"&CONT_USER="+contrasena.getText().toString() );
            }
        });

        //ver contraseña
        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( status2==0 ){
                    contrasena.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    status2=1;
                }else{
                    contrasena.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    status2=0;
                }
            }
        });
    }

    public void buscarUsuario(String URL){
        String nombreU = nombre.getText().toString();
        JsonArrayRequest jr = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject object = null;
                    for( int i=0; i<response.length(); i++ ){
                        object = response.getJSONObject(i);
                        aux1 = object.getString("NOM_USER") ;
                        aux3++;
                        Toast.makeText(getApplicationContext(), "Usuario ya existe", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "vacio", Toast.LENGTH_SHORT).show();
                }
                if( nombre.getText().toString().isEmpty() || contrasena.getText().toString().isEmpty() || direccion.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Complete los campos", Toast.LENGTH_SHORT).show();
                }else{
                    if( aux3 == 0  || aux3 < 1 ){
                        insertarUser("https://actividadesuta.000webhostapp.com/registrarUsuario.php");
                        Toast.makeText(getApplicationContext(), "REGISTRADO...", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(in);
                        aux3=0;
                    }
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

    public void limpiar(){
        nombre.setText("");
        contrasena.setText("");
        direccion.setText("");
    }



}
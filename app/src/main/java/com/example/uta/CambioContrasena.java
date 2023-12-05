package com.example.uta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CambioContrasena extends AppCompatActivity {

    EditText contraAn, contraN1, contraN2;
    Button btnCambio;
    ImageButton btnAc, btnN1, btnN2;
    RequestQueue queue;
    String aux1;
    int aux3=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contrasena);
        contraAn = (EditText) findViewById(R.id.txtContraActual);
        contraN1 = (EditText) findViewById(R.id.txtContraNueva1);
        contraN2 = (EditText) findViewById(R.id.txtContraNueva2);

        btnCambio= (Button) findViewById(R.id.btnCambioContrasena);
        btnAc= (ImageButton) findViewById(R.id.btnContrasenaActual);
        btnN1= (ImageButton) findViewById(R.id.btnContrasenaN1);
        btnN2= (ImageButton) findViewById(R.id.btnComtrasenaN2);

        verContrasenas();

    }

    private void updateCon(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Actualizado", Toast.LENGTH_SHORT).show();
                limpiarCampos();
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
                parametros.put("CONT_USER", contraAn.getText().toString());
                parametros.put("CONT_USERN",contraN1.getText().toString());
                return parametros;
            }
        };
        queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    public void click( View view ){
        if(  validarCampos() ){
            buscarContrasena("http://actividadesuta.000webhostapp.com/buscarContraseña.php?CONT_USER="+contraAn.getText().toString().trim());
        }else{
            Toast.makeText(this, "Complete los campos necesarios", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmarContrasena(){
        int compararCon;
        String aux1, aux2;
        aux1 = contraN1.getText().toString().trim();
        aux2 = contraN2.getText().toString().trim();
        compararCon = aux1.compareTo(aux2);
            if( compararCon == 0 ){
                updateCon("https://actividadesuta.000webhostapp.com/cambioContra.php");
                Intent i = new Intent(getApplicationContext(),Menu.class);
                startActivity(i);

            }else{
                Toast.makeText(this, "Las contraseñas no son las mismas", Toast.LENGTH_SHORT).show();
                contraN2.requestFocus();
            }
    }

    public boolean validarCampos(){
        if( contraAn.getText().toString().isEmpty() || contraN1.getText().toString().isEmpty() || contraN2.getText().toString().isEmpty() ){
            return false;
        }else{
            return true;
        }
    }


    int status1=1;
    int status2=0;
    int status3=0;
    public void verContrasenas(){
        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( status1==0 ){
                    contraAn.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    status1=1;
                }else{
                    contraAn.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    status1=0;

                }
            }
        });

        btnN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( status2==0 ){
                    contraN1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    status2=1;
                }else{
                    contraN1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    status2=0;
                }
            }
        });

        btnN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( status3==0 ){
                    contraN2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    status3=1;
                }else{
                    contraN2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    status3=0;
                }
            }
        });
    }

    public void buscarContrasena(String URL){
        JsonArrayRequest jr = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject object = null;
                    for( int i=0; i<response.length(); i++ ){
                        object = response.getJSONObject(i);
                        aux3++;
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Error al extraer de la base de datos", Toast.LENGTH_SHORT).show();
                }
                if( aux3 == 0  || aux3 < 1 ){
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta...", Toast.LENGTH_SHORT).show();
                    contraAn.requestFocus();
                }else{
                    confirmarContrasena();
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

    void limpiarCampos(){
        contraAn.setText("");
        contraN1.setText("");
        contraN2.setText("");
    }

}
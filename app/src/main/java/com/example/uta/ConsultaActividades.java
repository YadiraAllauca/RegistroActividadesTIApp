package com.example.uta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ConsultaActividades extends AppCompatActivity {

    ArrayList<Actividades> listAct;
    ArrayList<Actividades> listActBuscado;
    RequestQueue queue;
    RecyclerView reciclerAct;
    ImageButton btnCarga;
    EditText consultaF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_actividades);

        reciclerAct = (RecyclerView) findViewById(R.id.rcActividades);

        //reciclerAct.setHasFixedSize(true);
        reciclerAct.setLayoutManager( new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        btnCarga = (ImageButton) findViewById(R.id.btnBuscar);
        consultaF = (EditText)findViewById(R.id.txtFechaConsulta);
        consultaF.setEnabled(false);
        listAct = new ArrayList<>();
        listActBuscado = new ArrayList<>();

        cargarD("https://actividadesuta.000webhostapp.com/Consulta.php");
        accionBotones();

    }

    int aux1=0;
    private void buscare(String URL) {
        JsonArrayRequest jr = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = null;
                try {
                    for( int i=0; i<response.length(); i++ ){

                        object = response.getJSONObject(i);
                        listAct.add(new Actividades(object.getString("TIP_ACTV"),
                                object.getString("ACTIVIDAD"),
                                object.getString("FEC_ACTV"),
                                object.getString("NOM_REGISTRA"),
                                object.getString("NOM_PER_SOLICITA"),
                                object.getString("ESTADO"),
                                object.getString("NUM_HOR"),
                                object.getString("OBSERVACIONES") ));

                        aux1++;
                    }

                    if( aux1 == 0 || aux1 <= 0   ){
                        Toast.makeText(getApplicationContext(), "No se encontraron datos", Toast.LENGTH_SHORT).show();
                        aux1=0;
                    }else{
                        AdaptadorActividades adapter = new AdaptadorActividades(getApplicationContext(),listAct);
                        reciclerAct.setAdapter(adapter);
                        aux1=0;
                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "vacio", Toast.LENGTH_SHORT).show();
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

    private void accionBotones(){
        btnCarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( consultaF.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Agrege una una fecha para busqueda", Toast.LENGTH_SHORT).show();
                }else{
                    if( listAct.size() > 0 ){
                        listAct.clear();
                    }
                    buscare("https://actividadesuta.000webhostapp.com/BuscarActividad.php?FEC_ACTV="+consultaF.getText().toString().trim());
                }
            }
        });
    }


    //http://localhost/UTA/Consulta.php
    public void cargarD(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"Cargando",Toast.LENGTH_SHORT).show();
                try {
                    JSONArray cadena=new JSONArray(response);
                    for (int i=0;i<cadena.length(); i++){
                        JSONObject object = cadena.getJSONObject(i);
                        listActBuscado.add(new Actividades(object.getString("TIP_ACTV"),
                                object.getString("ACTIVIDAD"),
                                object.getString("FEC_ACTV"),
                                object.getString("NOM_REGISTRA"),
                                object.getString("NOM_PER_SOLICITA"),
                                object.getString("ESTADO"),
                                object.getString("NUM_HOR"),
                                object.getString("OBSERVACIONES") ));
                    }
                    AdaptadorActividades adapters = new AdaptadorActividades(getApplicationContext(),listActBuscado);
                    reciclerAct.setAdapter(adapters);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "NO se encontraron datos", Toast.LENGTH_SHORT).show();
            }
        });
        queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    public void fecha(View v){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                consultaF.setText(ano+"-"+(mes+1)+"-"+dia);
            }
        },year, month, day);
        dpd.show();
    }
}
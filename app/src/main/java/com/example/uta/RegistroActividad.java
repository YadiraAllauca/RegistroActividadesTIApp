package com.example.uta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class RegistroActividad extends AppCompatActivity {

    Spinner spTipo, spAtc, spSolicitante, spEstado;
    EditText fecha,pRegistra,hora,obs;
    RequestQueue queue;

    String[] tipoAct = {"CONFIGURACIÓN","SOPORTE SI","REUNION"};
    String[] actividad1 = {"TELEFONO IP","SERVIDOR BD", "SERVIDOR CORREO", "PC", "TABLET" };
    String[] actividad2 = {"VENTAS","COMPRAS","FACTURACIÓN","NOMINA","INVENTARIOS" };
    String[] actividad3 = {"PLANIFICACIÓN","DIARIA" };
    String[] solicitante = {"YADIRA AYAUCA","MELANY RECALDE","DALEMBERT BRAVO"};
    String[] estado = {"APROBADO","PENDIENTE","IMPLEMENTACIÓN","FINALIZADO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_actividad);

        spTipo = (Spinner) findViewById(R.id.spTipoActv);
        spAtc = (Spinner) findViewById(R.id.spActv);
        spSolicitante = (Spinner) findViewById(R.id.spPersonaSolicita);
        spEstado = (Spinner) findViewById(R.id.spEstado);
        fecha = (EditText) findViewById(R.id.txtFecha);
        pRegistra = (EditText) findViewById(R.id.txtPersonaRegistra);
        hora = (EditText) findViewById(R.id.txtHoras);
        obs = (EditText) findViewById(R.id.txtOnservaciones);
        fecha.setEnabled(false);

        //control Spinner spTipoActv
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                 String auxT = spTipo.getItemAtPosition(posicion).toString();
                if( auxT.equals("CONFIGURACIÓN") ){
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, actividad1);
                    spAtc.setAdapter(adapter2);
                }else if( auxT.equals("SOPORTE SI") ){
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, actividad2);
                    spAtc.setAdapter(adapter2);
                }else if( auxT.equals("REUNION") ){
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, actividad3);
                    spAtc.setAdapter(adapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        cargarSpinners();

        //recuperar nombre del usuario
        pRegistra.setEnabled(false);
        String nombreU = getIntent().getStringExtra("user");
        pRegistra.setText(nombreU);

    }

    private void cargarSpinners(){
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipoAct);
        spTipo.setAdapter(adapter1);

        /*ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, actividad);
        spAtc.setAdapter(adapter2);*/

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, solicitante);
        spSolicitante.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estado);
        spEstado.setAdapter(adapter4);
    }

    private void insertar(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Insertado", Toast.LENGTH_SHORT).show();
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
                parametros.put("TIP_ACTV", spTipo.getSelectedItem().toString());
                parametros.put("ACTIVIDAD", spAtc.getSelectedItem().toString());
                parametros.put("FEC_ACTV", fecha.getText().toString());
                parametros.put("NOM_REGISTRA", pRegistra.getText().toString());
                parametros.put("NOM_PER_SOLICITA", spSolicitante.getSelectedItem().toString());
                parametros.put("ESTADO", spEstado.getSelectedItem().toString());
                parametros.put("NUM_HOR", hora.getText().toString());
                parametros.put("OBSERVACIONES", obs.getText().toString());

                return parametros;
            }
        };
        queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    //conecion con e menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insert, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if( id == R.id.opGuardar ){
            if( validarCampos() ){
                insertar("http://actividadesuta.000webhostapp.com/RegistrarActividad.php");

            }else{
                Toast.makeText(getApplicationContext(), "Complete los campos", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean validarCampos(){
        if( fecha.getText().toString().isEmpty() || pRegistra.getText().toString().isEmpty() || hora.getText().toString().isEmpty() || obs.getText().toString().isEmpty() ){
            return false;
        }else{
            return true;
        }
    }

    private void limpiarCampos(){
        fecha.setText("");
        pRegistra.setText("");
        hora.setText("");
        obs.setText("");
    }

    public void fecha(View v){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                fecha.setText(ano+"/"+(mes+1)+"/"+dia);
            }
        },year, month, day);
        dpd.show();
    }
}
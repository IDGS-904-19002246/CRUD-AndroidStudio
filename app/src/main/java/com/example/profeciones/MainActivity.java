package com.example.profeciones;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.profeciones.adapter.Adaptador;
import com.example.profeciones.api.api_inter;
import com.example.profeciones.api.retro;
import com.example.profeciones.models.ClsProfeciones;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private FloatingActionButton button_add;

    private EditText nombre, descripcion, nivel_educativo, salario_promedio, puestos_disponibles, titulados;
    private Spinner ingles_nivel, estado;
    private Button CANCEL,ADD, DELETE;
    private String str_estado,str_ingles_nivel;

    private List<ClsProfeciones> lista;
    private Adaptador adaptador;
    api_inter api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_add = findViewById(R.id.button_add);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(this, 1));
//    -----------------------------------------------------------------------------------------

        lista = new ArrayList<>();
//        datos_basura();
//        adaptador = new Adaptador(this, lista);
//        recycler.setAdapter(adaptador);

        api = retro.getClient().create(api_inter.class);
        datos();
        adaptador = new Adaptador(MainActivity.this,lista);
        recycler.setAdapter(adaptador);
//        Call<List<ClsProfeciones>> call = api.TODAS();
//        call.enqueue(new Callback<List<ClsProfeciones>>() {
//            @Override
//            public void onResponse(Call<List<ClsProfeciones>> call, Response<List<ClsProfeciones>> response) {
//                lista = response.body();
//                adaptador = new Adaptador(MainActivity.this,lista);
//                recycler.setAdapter(adaptador);
//            }
//
//            @Override
//            public void onFailure(Call<List<ClsProfeciones>> call, Throwable t) {
//
//            }
//        });
//    -----------------------------------------------------------------------------------------
        //            Toast.makeText(this, "boton uwu", Toast.LENGTH_SHORT).show();
        button_add.setOnClickListener(view -> {
            add();
        });
        adaptador.setOnClickItem(pro -> {
            alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            final View V = LayoutInflater.from(MainActivity.this).inflate(R.layout.profeciones_form, null);

            DELETE = V.findViewById(R.id.delete);
            DELETE.setVisibility(View.VISIBLE);

            alertDialogBuilder.setView(V);
            alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        });


    }

    private void add() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        final View V = LayoutInflater.from(this).inflate(R.layout.profeciones_form, null);
//    -----------------------------------------------------------------------------------------
        ADD = V.findViewById(R.id.save);
        DELETE = V.findViewById(R.id.delete);
        DELETE.setVisibility(View.INVISIBLE);
        nombre = V.findViewById(R.id.nombre);
        descripcion = V.findViewById(R.id.descripcion);
        nivel_educativo = V.findViewById(R.id.nivel_educativo);
        salario_promedio = V.findViewById(R.id.salario_promedio);
        puestos_disponibles = V.findViewById(R.id.puestos_disponibles);
        titulados = V.findViewById(R.id.titulados);
        ingles_nivel = V.findViewById(R.id.ingles_nivel);
        estado = V.findViewById(R.id.estado);
//    -----------------------------------------------------------------------------------------
        ArrayAdapter<CharSequence> adapter_estado = ArrayAdapter.createFromResource(this, R.array.estado_opciones, android.R.layout.simple_spinner_item);
        adapter_estado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado.setAdapter(adapter_estado);
        estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_estado = (String) estado.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter<CharSequence> adapter_ingles_nivel = ArrayAdapter.createFromResource(this, R.array.ingles_nivel_opciones, android.R.layout.simple_spinner_item);
        adapter_ingles_nivel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ingles_nivel.setAdapter(adapter_ingles_nivel);
        ingles_nivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_ingles_nivel = (String) ingles_nivel.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });



//    -----------------------------------------------------------------------------------------

        ADD.setOnClickListener(v -> {
            if (
                    String.valueOf(nombre.getText()).isEmpty() ||
                    String.valueOf(descripcion.getText()).isEmpty() ||
                    String.valueOf(nivel_educativo.getText()).isEmpty() ||
                    String.valueOf(salario_promedio.getText()).isEmpty() ||
                    String.valueOf(puestos_disponibles.getText()).isEmpty() ||
                    String.valueOf(titulados.getText()).isEmpty() ||
                    str_ingles_nivel.isEmpty() ||
                    str_estado.isEmpty()
            ){
                Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            }else{
                Call<String> call = api.NUEVA(
                        String.valueOf(nombre.getText()),
                        String.valueOf(descripcion.getText()),
                        String.valueOf(nivel_educativo.getText()),
                        String.valueOf(salario_promedio.getText()),
                        String.valueOf(puestos_disponibles.getText()),
                        String.valueOf(titulados.getText()),
                        str_ingles_nivel,
                        str_estado
                );
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String profesiones = response.body();
                        Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {}
                });
            }


        });
//    -----------------------------------------------------------------------------------------
        alertDialogBuilder.setView(V);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void datos(){
        lista.add(new ClsProfeciones(1,"juan","56","nivel_educativo",156,165,20,"Basico","Verificado"));
        lista.add(new ClsProfeciones(2,"juan jose","56","nivel_educativo",156,165,20,"Basico","Verificado"));
        lista.add(new ClsProfeciones(3,"juan jose de jesus","56","nivel_educativo",156,165,20,"Basico","Verificado"));
    }
//    public boolean validar(String[] campos){
//
//        for (String c:campos) {
//            if (c == "" && c == " " && c == null){
//                return false;
//            }
//        }
//        return true;
//    }
}
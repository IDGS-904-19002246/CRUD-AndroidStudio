package com.example.profeciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.profeciones.adapter.AdaBDs;
import com.example.profeciones.api.api_dbs;
import com.example.profeciones.api.retro;
import  com.example.profeciones.databinding.ActivityDbsBinding;
import com.example.profeciones.databinding.DbsFormBinding;
import com.example.profeciones.models.ClsBDs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DBsActivity extends AppCompatActivity {
    ActivityDbsBinding b;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    //    --------------------------------------------------------------------
    private List<ClsBDs> lista = new ArrayList<>();
    private List<String> estado_db = new ArrayList<>(Arrays.asList(
            "En servicio",
            "Fuera de servicio",
            "Eliminada"
    ));
    private List<String> tipo_db = new ArrayList<>(Arrays.asList(
            "SQL",
            "NO-SQL"
    ));
    public String str_estado, str_tipo;
    private AdaBDs adaptador = new AdaBDs(this,lista);;
    api_dbs api_dbs = retro.getClient().create(api_dbs.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbs);
        b = ActivityDbsBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.recycler.setLayoutManager(new LinearLayoutManager(this));
        b.recycler.setAdapter(adaptador);
        select();
        b.BACK.setOnClickListener(v -> {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });
        b.ADD.setOnClickListener(v -> {
            add();
        });
    }
    //    --------------------------------------------------------------------

    private void select(){
        Call<List<ClsBDs>> call = api_dbs.TODAS();
        call.enqueue(new Callback<List<ClsBDs>>() {
            @Override
            public void onResponse(Call<List<ClsBDs>> call, Response<List<ClsBDs>> response) {
                adaptador.actualizarDatos(response.body());
                adaptador.setOnClickItem(item -> {
                    edit(item);
                });
            }
            @Override
            public void onFailure(Call<List<ClsBDs>> call, Throwable t) {}
        });
    }
    private void add() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        DbsFormBinding b2 = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dbs_form, null, false);
        final View V = b2.getRoot();
        alertDialogBuilder.setView(V);
//    -----------------------------------------------------------------------------------------
        b2.delete.setVisibility(View.INVISIBLE);
        ArrayAdapter<CharSequence> estado_db = ArrayAdapter.createFromResource(this, R.array.estado_db, android.R.layout.simple_spinner_item);
        estado_db.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b2.estado.setAdapter(estado_db);
        b2.estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {str_estado = (String) b2.estado.getItemAtPosition(position);}
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        ArrayAdapter<CharSequence> tipo_db = ArrayAdapter.createFromResource(this, R.array.tipo_db, android.R.layout.simple_spinner_item);
        tipo_db.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b2.tipo.setAdapter(tipo_db);
        b2.tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_tipo = (String) b2.tipo.getItemAtPosition(position);}
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

//    -----------------------------------------------------------------------------------------
        b2.save.setOnClickListener(v -> {
            if (
                b2.nombre.getText().toString().isEmpty() ||
                b2.propietario.getText().toString().isEmpty() ||
                b2.descripcion.getText().toString().isEmpty() ||
                b2.tamanoMb.getText().toString().isEmpty() ||
                str_estado.isEmpty() || str_tipo.isEmpty()
            ){
                Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            }else{
                Call<String> call = api_dbs.NUEVA(
                    b2.nombre.getText().toString(),str_tipo,
                    b2.propietario.getText().toString(), b2.descripcion.getText().toString(),
                    Integer.parseInt(String.valueOf(b2.tamanoMb.getText())),str_estado
                );
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        select();
                        alertDialog.dismiss();
                    }
                    @Override public void onFailure(Call<String> call, Throwable t) {}
                });

            }
        });
//    -----------------------------------------------------------------------------------------
        alertDialogBuilder.setView(V);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void edit(ClsBDs item) {
        alertDialogBuilder = new AlertDialog.Builder(this);
        DbsFormBinding b2 = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dbs_form, null, false);
        final View V = b2.getRoot();
        alertDialogBuilder.setView(V);
//    -----------------------------------------------------------------------------------------
        ArrayAdapter<CharSequence> AdaEstado_db = ArrayAdapter.createFromResource(this, R.array.estado_db, android.R.layout.simple_spinner_item);
        AdaEstado_db.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b2.estado.setAdapter(AdaEstado_db);
        b2.estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {str_estado = (String) b2.estado.getItemAtPosition(position);}
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        ArrayAdapter<CharSequence> AdaTipo_db = ArrayAdapter.createFromResource(this, R.array.tipo_db, android.R.layout.simple_spinner_item);
        AdaTipo_db.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b2.tipo.setAdapter(AdaTipo_db);
        b2.tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_tipo = (String) b2.tipo.getItemAtPosition(position);}
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        b2.nombre.setText(item.nombre);
        b2.tipo.setSelection(tipo_db.indexOf(item.tipo));
        b2.propietario.setText(item.propietario);
        b2.descripcion.setText(item.descripcion);
        b2.tamanoMb.setText(String.valueOf(item.tamano_mb));
        b2.estado.setSelection(estado_db.indexOf(item.estado));

//    -----------------------------------------------------------------------------------------
        b2.delete.setOnClickListener(v -> {
            Call<String> call = api_dbs.BORRAR(item.id_DB);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    select();
                    alertDialog.dismiss();
                }
                @Override public void onFailure(Call<String> call, Throwable t) {}
            });
        });
        b2.save.setOnClickListener(v -> {
            if (
                b2.nombre.getText().toString().isEmpty() ||
                b2.propietario.getText().toString().isEmpty() ||
                b2.descripcion.getText().toString().isEmpty() ||
                b2.tamanoMb.getText().toString().isEmpty() ||
                str_estado.isEmpty() || str_tipo.isEmpty()
            ){
                Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            }else{
                item.nombre = b2.nombre.getText().toString();
                item.tipo = str_tipo;
                item.propietario = b2.propietario.getText().toString();
                item.descripcion = b2.descripcion.getText().toString();
                item.tamano_mb = Integer.parseInt(b2.tamanoMb.getText().toString());
                item.estado = (str_estado);

                Call<String> call = api_dbs.EDITAR(item);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        select();
                        alertDialog.dismiss();
                    }
                    @Override public void onFailure(Call<String> call, Throwable t) {}
                });

            }
        });
//    -----------------------------------------------------------------------------------------
        alertDialogBuilder.setView(V);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
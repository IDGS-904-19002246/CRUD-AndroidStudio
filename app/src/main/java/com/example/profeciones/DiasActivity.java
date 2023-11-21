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
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.profeciones.adapter.AdaDiasFestivos;
import com.example.profeciones.api.retro;
import com.example.profeciones.api.api_dia;
import com.example.profeciones.databinding.DiaFormBinding;
import  com.example.profeciones.databinding.ActivityDiasBinding;
import com.example.profeciones.models.ClsDiasFestivos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiasActivity extends AppCompatActivity {
    ActivityDiasBinding b;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    //    --------------------------------------------------------------------
    private List<ClsDiasFestivos> lista = new ArrayList<>();
    private List<String> list_tipo = new ArrayList<>(Arrays.asList("religion","necional","regional"));
    private List<String> list_estado = new ArrayList<>(Arrays.asList("No Verificado", "Verificado", "Eliminado"));
    public String str_estado, str_tipo;
    private Calendar calendar;
    private AdaDiasFestivos adaptador = new AdaDiasFestivos(this,lista);;
    api_dia api_dia = retro.getClient().create(api_dia.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias);
        b = ActivityDiasBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.recycler.setLayoutManager(new LinearLayoutManager(this));
        b.recycler.setAdapter(adaptador);
        select();
        calendar = Calendar.getInstance();
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
        Call<List<ClsDiasFestivos>> call = api_dia.TODAS();
        call.enqueue(new Callback<List<ClsDiasFestivos>>() {
            @Override
            public void onResponse(Call<List<ClsDiasFestivos>> call, Response<List<ClsDiasFestivos>> response) {
                adaptador.actualizarDatos(response.body());
                adaptador.setOnClickItem(item -> {
                    edit(item);
                });
            }
            @Override
            public void onFailure(Call<List<ClsDiasFestivos>> call, Throwable t) {}
        });
    }

    private void add() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        DiaFormBinding b2 = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dia_form, null, false);
        final View V = b2.getRoot();
        alertDialogBuilder.setView(V);
//    -----------------------------------------------------------------------------------------
        b2.delete.setVisibility(View.INVISIBLE);
        ArrayAdapter<CharSequence> estado = ArrayAdapter.createFromResource(this, R.array.estado_opciones, android.R.layout.simple_spinner_item);
        estado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b2.estado.setAdapter(estado);
        b2.estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_estado = (String) b2.estado.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        ArrayAdapter<CharSequence> tipo = ArrayAdapter.createFromResource(this, R.array.tipo_dia, android.R.layout.simple_spinner_item);
        tipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b2.tipo.setAdapter(tipo);
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
                b2.descripcion.getText().toString().isEmpty() ||
                b2.pais.getText().toString().isEmpty() ||
                str_estado.isEmpty() || str_tipo.isEmpty()
            ){
                Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            }else{
                Call<String> call = api_dia.NUEVA(
                    b2.nombre.getText().toString(),
                    Fecha(b2.fecha),
                    b2.descripcion.getText().toString(),
                    b2.pais.getText().toString(),
                    str_tipo, str_estado
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

    private void edit(ClsDiasFestivos item){
        alertDialogBuilder = new AlertDialog.Builder(this);
        DiaFormBinding b2 = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dia_form, null, false);
        final View V = b2.getRoot();
        alertDialogBuilder.setView(V);
//    -----------------------------------------------------------------------------------------
        ArrayAdapter<CharSequence> estado = ArrayAdapter.createFromResource(this, R.array.estado_opciones, android.R.layout.simple_spinner_item);
        estado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b2.estado.setAdapter(estado);
        b2.estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_estado = (String) b2.estado.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        ArrayAdapter<CharSequence> tipo = ArrayAdapter.createFromResource(this, R.array.tipo_dia, android.R.layout.simple_spinner_item);
        tipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b2.tipo.setAdapter(tipo);
        b2.tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_tipo = (String) b2.tipo.getItemAtPosition(position);}
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });



        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = formato.parse(item.fecha);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            b2.fecha.updateDate(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        b2.nombre.setText(item.nombre);

        b2.descripcion.setText(item.descripcion);
        b2.pais.setText(item.pais);
        b2.tipo.setSelection(list_tipo.indexOf(item.tipo));
        b2.estado.setSelection(list_estado.indexOf(item.estado));
//    -----------------------------------------------------------------------------------------
        b2.delete.setOnClickListener(v -> {
            Call<String> call = api_dia.BORRAR(item.id_diaF);
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
                            b2.descripcion.getText().toString().isEmpty() ||
                            b2.pais.getText().toString().isEmpty() ||
                            str_estado.isEmpty() || str_tipo.isEmpty()
            ){
                Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            }else{
                Call<String> call = api_dia.NUEVA(
                        b2.nombre.getText().toString(),
                        Fecha(b2.fecha),
                        b2.descripcion.getText().toString(),
                        b2.pais.getText().toString(),
                        str_tipo, str_estado
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

    private String Fecha(DatePicker f){
        int Y = f.getYear();
        int M = f.getMonth() + 1;
        int D = f.getDayOfMonth();
        if (M <= 9){
            if (D <= 9){
                return Y+"-0"+M+"-0"+D;
            }else{
                return Y+"-0"+M+"-"+D;
            }
        }else{
            if (D <= 9){
                return Y+"-"+M+"-0"+D;
            }else{
                return Y+"-"+M+"-"+D;
            }
        }
    }
}
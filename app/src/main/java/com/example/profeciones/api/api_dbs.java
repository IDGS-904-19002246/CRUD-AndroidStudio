package com.example.profeciones.api;

import com.example.profeciones.models.ClsBDs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface api_dbs {
    @GET("basesDatos.php")
    Call<List<ClsBDs>> TODAS();

    @FormUrlEncoded
    @POST("basesDatos.php")
    Call<String>NUEVA(
            @Field("nombre")String nombre,
            @Field("tipo")String tipo,
            @Field("propietario")String propietario,
            @Field("descripcion")String descripcion,
            @Field("tamano_mb")int tamano_mb,
            @Field("estado")String estado
    );

    @PUT("basesDatos.php")
    Call<String>EDITAR(
            @Body ClsBDs clsBDs
    );

    @DELETE("basesDatos.php")
    Call<String>BORRAR(@Query("id") int Id);
}

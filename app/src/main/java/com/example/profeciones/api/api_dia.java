package com.example.profeciones.api;

import com.example.profeciones.models.ClsDiasFestivos;

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

public interface api_dia {
    @GET("diasFestivos.php")
    Call<List<ClsDiasFestivos>> TODAS();

    @FormUrlEncoded
    @POST("diasFestivos.php")
    Call<String>NUEVA(
            @Field("nombre")String nombre,
            @Field("fecha")String fecha,
            @Field("descripcion")String descripcion,
            @Field("pais")String pais,
            @Field("tipo")String tipo,
            @Field("estado")String estado
    );

    @PUT("diasFestivos.php")
    Call<String>EDITAR(
            @Body ClsDiasFestivos clsDiasFestivos
    );

    @DELETE("diasFestivos.php")
    Call<String>BORRAR(@Query("id") int Id);
}

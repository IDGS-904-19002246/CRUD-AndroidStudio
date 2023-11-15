package com.example.profeciones.api;

import com.example.profeciones.models.ClsProfeciones;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface api_inter {
    @GET("profeciones.php")
    Call<List<ClsProfeciones>> TODAS();

    @FormUrlEncoded
    @POST("profeciones.php")
    Call<String>NUEVA(
        @Field("nombre")String nombre,
        @Field("descripcion")String descripcion,
        @Field("nivel_educativo")String nivel_educativo,
        @Field("salario_promedio")String salario_promedio,
        @Field("puestos_disponibles")String puestos_disponibles,
        @Field("titulados")String titulados,
        @Field("ingles_nivel")String ingles_nivel,
        @Field("estado")String estado
    );

    //ACTUALIZAR PELI
    @PUT("profeciones.php")
    Call<String>EDITAR(
            @Body ClsProfeciones clsProfeciones
    );

    //BORRAR PELI
//    @DELETE("profeciones.php?id={Id}")
//    Call<String>BORRAR(@Path("Id") int Id);

    @DELETE("profeciones.php")
    Call<String> BORRAR(@Query("id") int Id);

}

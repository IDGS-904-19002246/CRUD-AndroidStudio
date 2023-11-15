package com.example.profeciones.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class retro {

//    private static final String URL = "http://192.168.137.207/profeciones_api/";
    private static final String URL = "http://192.168.1.10/profeciones_api/";

    private static Retrofit retro;
    public static Retrofit getClient(){
        if (retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}

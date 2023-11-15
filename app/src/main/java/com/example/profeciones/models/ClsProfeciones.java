package com.example.profeciones.models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class ClsProfeciones implements Parcelable {
    private int id_profecion;
    private String nombre;
    private String descripcion;
    private String nivel_educativo;

    private int salario_promedio;
    private int puestos_disponibles;
    private int titulados;
    private String ingles_nivel;
    private String estado;

    public ClsProfeciones(int id_profecion, String nombre, String descripcion, String nivel_educativo, int salario_promedio, int puestos_disponibles, int titulados, String ingles_nivel, String estado) {
        this.id_profecion = id_profecion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel_educativo = nivel_educativo;
        this.salario_promedio = salario_promedio;
        this.puestos_disponibles = puestos_disponibles;
        this.titulados = titulados;
        this.ingles_nivel = ingles_nivel;
        this.estado = estado;
    }

    protected ClsProfeciones(Parcel in) {
        id_profecion = in.readInt();
        nombre = in.readString();
        descripcion = in.readString();
        nivel_educativo = in.readString();
        salario_promedio = in.readInt();
        puestos_disponibles = in.readInt();
        titulados = in.readInt();
        ingles_nivel = in.readString();
        estado = in.readString();
    }

    public static final Creator<ClsProfeciones> CREATOR = new Creator<ClsProfeciones>() {
        @Override
        public ClsProfeciones createFromParcel(Parcel in) {
            return new ClsProfeciones(in);
        }

        @Override
        public ClsProfeciones[] newArray(int size) {
            return new ClsProfeciones[size];
        }
    };

    public int getId_profecion() {
        return id_profecion;
    }

    public void setId_profecion(int id_profecion) {
        this.id_profecion = id_profecion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNivel_educativo() {
        return nivel_educativo;
    }

    public void setNivel_educativo(String nivel_educativo) {
        this.nivel_educativo = nivel_educativo;
    }

    public int getSalario_promedio() {
        return salario_promedio;
    }

    public void setSalario_promedio(int salario_promedio) {
        this.salario_promedio = salario_promedio;
    }

    public int getPuestos_disponibles() {
        return puestos_disponibles;
    }

    public void setPuestos_disponibles(int puestos_disponibles) {
        this.puestos_disponibles = puestos_disponibles;
    }

    public int getTitulados() {
        return titulados;
    }

    public void setTitulados(int titulados) {
        this.titulados = titulados;
    }

    public String getIngles_nivel() {
        return ingles_nivel;
    }

    public void setIngles_nivel(String ingles_nivel) {
        this.ingles_nivel = ingles_nivel;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id_profecion);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(nivel_educativo);
        dest.writeInt(salario_promedio);
        dest.writeInt(puestos_disponibles);
        dest.writeInt(titulados);
        dest.writeString(ingles_nivel);
        dest.writeString(estado);
    }
}

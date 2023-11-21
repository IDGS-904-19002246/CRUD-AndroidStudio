package com.example.profeciones.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ClsBDs implements Parcelable {
    public int id_DB;
    public String nombre;
    public String tipo;
    public String propietario;
    public String descripcion;
    public int tamano_mb;
    public String estado;

    public ClsBDs(int id_DB, String nombre, String tipo, String propietario, String descripcion, int tamano_mb, String estado) {
        this.id_DB = id_DB;
        this.nombre = nombre;
        this.tipo = tipo;
        this.propietario = propietario;
        this.descripcion = descripcion;
        this.tamano_mb = tamano_mb;
        this.estado = estado;
    }

    protected ClsBDs(Parcel in) {
        id_DB = in.readInt();
        nombre = in.readString();
        tipo = in.readString();
        propietario = in.readString();
        descripcion = in.readString();
        tamano_mb = in.readInt();
        estado = in.readString();
    }

    public static final Creator<ClsBDs> CREATOR = new Creator<ClsBDs>() {
        @Override
        public ClsBDs createFromParcel(Parcel in) {
            return new ClsBDs(in);
        }

        @Override
        public ClsBDs[] newArray(int size) {
            return new ClsBDs[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id_DB);
        dest.writeString(nombre);
        dest.writeString(tipo);
        dest.writeString(propietario);
        dest.writeString(descripcion);
        dest.writeInt(tamano_mb);
        dest.writeString(estado);
    }
}

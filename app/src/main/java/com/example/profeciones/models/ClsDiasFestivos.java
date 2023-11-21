package com.example.profeciones.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ClsDiasFestivos implements Parcelable {
    public int id_diaF;
    public String nombre;
    public String fecha;
    public String descripcion;
    public String pais;
    public String tipo;
    public String estado;

    public ClsDiasFestivos(int id_diaF, String nombre, String fecha, String descripcion, String pais, String tipo, String estado) {
        this.id_diaF = id_diaF;
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.pais = pais;
        this.tipo = tipo;
        this.estado = estado;
    }

    protected ClsDiasFestivos(Parcel in) {
        id_diaF = in.readInt();
        nombre = in.readString();
        fecha = in.readString();
        descripcion = in.readString();
        pais = in.readString();
        tipo = in.readString();
        estado = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_diaF);
        dest.writeString(nombre);
        dest.writeString(fecha);
        dest.writeString(descripcion);
        dest.writeString(pais);
        dest.writeString(tipo);
        dest.writeString(estado);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ClsDiasFestivos> CREATOR = new Creator<ClsDiasFestivos>() {
        @Override
        public ClsDiasFestivos createFromParcel(Parcel in) {
            return new ClsDiasFestivos(in);
        }

        @Override
        public ClsDiasFestivos[] newArray(int size) {
            return new ClsDiasFestivos[size];
        }
    };
}

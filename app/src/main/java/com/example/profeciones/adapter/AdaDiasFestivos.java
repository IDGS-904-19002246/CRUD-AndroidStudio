package com.example.profeciones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.profeciones.R;
import com.example.profeciones.models.ClsBDs;
import com.example.profeciones.models.ClsDiasFestivos;

import java.util.List;

public class AdaDiasFestivos extends RecyclerView.Adapter<AdaDiasFestivos.Vista>{
    private Context C;
    private List<ClsDiasFestivos> Lista;
    public AdaDiasFestivos(Context C, List<ClsDiasFestivos> Lista) {
        this.C = C;
        this.Lista = Lista;
    }
    //    -----------------------------------------------------------------------------------------
    private AdaDiasFestivos.OnClickItem onClickItem;
    public interface OnClickItem { void onDiaClick(ClsDiasFestivos pro);}
    public void setOnClickItem(AdaDiasFestivos.OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }
    public void actualizarDatos(List<ClsDiasFestivos> nuevosDatos) {
        Lista = nuevosDatos;
        notifyDataSetChanged();
    }
    //    -----------------------------------------------------------------------------------------
    @NonNull
    @Override
    public AdaDiasFestivos.Vista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(C).inflate(R.layout.elemento, parent, false);
        return new Vista(V);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaDiasFestivos.Vista holder, int position) {
        final ClsDiasFestivos item = Lista.get(position);
        holder.Nombre.setText(item.nombre+" ["+item.pais+"]");
        holder.Salario.setText("Fecha: " +item.fecha+". "+ item.descripcion);
        holder.Estado.setText(item.estado);

        holder.itemView.setOnClickListener(view ->{
            onClickItem.onDiaClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return Lista.size();
    }
    //    -----------------------------------------------------------------------------------------
    public class Vista extends RecyclerView.ViewHolder{

        TextView Nombre;
        TextView Salario;
        TextView Estado;

        public Vista(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.nombre);
            Salario = itemView.findViewById(R.id.salario_promedio);
            Estado = itemView.findViewById(R.id.estado);
        }
    }
}

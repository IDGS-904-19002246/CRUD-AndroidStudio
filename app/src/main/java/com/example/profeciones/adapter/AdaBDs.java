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
import com.example.profeciones.models.ClsProfeciones;

import java.util.List;

public class AdaBDs extends RecyclerView.Adapter<AdaBDs.Vista>{
    private Context C;
    private List<ClsBDs> Lista;
    public AdaBDs(Context C, List<ClsBDs> Lista) {
        this.C = C;
        this.Lista = Lista;
    }
    //    -----------------------------------------------------------------------------------------
    private AdaBDs.OnClickItem onClickItem;
    public interface OnClickItem { void onClick(ClsBDs pro);}
    public void setOnClickItem(AdaBDs.OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }
    public void actualizarDatos(List<ClsBDs> nuevosDatos) {
        Lista = nuevosDatos;
        notifyDataSetChanged();
    }
    //    -----------------------------------------------------------------------------------------
    @NonNull
    @Override
    public AdaBDs.Vista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(C).inflate(R.layout.elemento, parent, false);
        return new Vista(V);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaBDs.Vista holder, int position) {
        final ClsBDs item = Lista.get(position);
        holder.Nombre.setText(item.nombre+" ["+item.tipo+"]");
        holder.Salario.setText("Por: "+item.propietario+", "+item.descripcion);
        holder.Estado.setText(item.estado);

        holder.itemView.setOnClickListener(view ->{
            onClickItem.onClick(item);
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

package com.example.profeciones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.profeciones.R;
import com.example.profeciones.models.ClsProfeciones;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.Vista> {
    private Context C;
    private List<ClsProfeciones> Lista;
    public Adaptador(Context C, List<ClsProfeciones> Lista) {
        this.C = C;
        this.Lista = Lista;
    }
//    -----------------------------------------------------------------------------------------
    private  OnClickItem onClickItem;

    public interface OnClickItem { void onPetClick(ClsProfeciones pro);}

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }
//    -----------------------------------------------------------------------------------------
    @Override
    public Adaptador.Vista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(C).inflate(R.layout.elemento, parent, false);
        return new Vista(V);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.Vista holder, int position) {
        final ClsProfeciones item = Lista.get(position);
        holder.Nombre.setText(item.getNombre());
        holder.Salario.setText("$ "+String.valueOf(item.getSalario_promedio())+".00");
        holder.Estado.setText(item.getEstado());


//        holder.petNameTextView.setText(pet.getName());
        holder.itemView.setOnClickListener(view ->{
            onClickItem.onPetClick(item);
//            Toast.makeText(C, "# "+item.getNombre(), Toast.LENGTH_SHORT).show();
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

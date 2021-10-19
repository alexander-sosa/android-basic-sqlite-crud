package com.example.sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.modelos.ModeloItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewDataHolder> {
    List<ModeloItem> lista;

    public Adaptador(List<ModeloItem> lista) {
        this.lista = lista;
    }

    public void setListaDeProductos (ArrayList<ModeloItem> listaDeProductos){
        this.lista = listaDeProductos;
    }


    @NonNull
    @Override
    public Adaptador.ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ViewDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewDataHolder holder, int position) {
        holder.tvNombre.setText(lista.get(position).getNombre());
        holder.tvExistencia.setText(String.valueOf(lista.get(position).getExistencia()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewDataHolder extends RecyclerView.ViewHolder{
        TextView tvNombre, tvExistencia;

        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvExistencia = itemView.findViewById(R.id.tvExistencia);
        }
    }
}

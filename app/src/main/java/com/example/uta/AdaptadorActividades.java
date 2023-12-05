package com.example.uta;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorActividades extends RecyclerView.Adapter<AdaptadorActividades.ViewHolderDatos> {
    List<Actividades> actv;
    Context context;

    public AdaptadorActividades(Context context, List<Actividades> lsActv ) {
        this.context=context;
        this.actv=lsActv;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.actividades, null, false);
        return new ViewHolderDatos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asiganarDatos(actv.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String nombre =  actv.get(position).getNombre();
                Intent intent = new Intent(holder.itemView.getContext(), Datos.class);
                intent.putExtra("actividades", actv.get(position).toString());
                holder.itemView.getContext().startActivity(intent);
                //Toast.makeText(holder.itemView.getContext(), actv.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return actv.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tipo,activida;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            tipo = itemView.findViewById(R.id.txtTipoActividad);
            activida = itemView.findViewById(R.id.txtActividad);
        }

        public void asiganarDatos( Actividades act ){
            //imagenes.setBackgroundResource(drawable.ic_mas);
            tipo.setText(act.getTipoActv());
            activida.setText(act.getEstado());
        }
    }
}

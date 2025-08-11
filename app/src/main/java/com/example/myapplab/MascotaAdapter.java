package com.example.myapplab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private ArrayList<Mascota> mascotas;

    public MascotaAdapter(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);

        holder.tvNombre.setText(mascota.getNombre());
        holder.tvRating.setText(String.valueOf(mascota.getRating()));
        holder.imgFoto.setImageResource(mascota.getFoto());

        holder.btnLike.setOnClickListener(view -> {
            // Aumenta rating localmente
            mascota.aumentarRating();
            holder.tvRating.setText(String.valueOf(mascota.getRating()));

            // Persistencia: insertar o actualizar en BD
            MascotaDbHelper db = new MascotaDbHelper(holder.itemView.getContext());

            try {
                // Si ya existe una fila con mismo nombre+foto -> actualizar
                Mascota existente = db.findMascotaByNombreAndFoto(mascota.getNombre(), mascota.getFoto());
                if (existente != null) {
                    // Actualizamos el rating con el valor actual del objeto
                    existente.setRating(mascota.getRating());
                    db.updateMascota(existente);
                    mascota.setId(existente.getId());
                } else {
                    // Insertamos nueva fila
                    long newId = db.insertMascota(mascota);
                    mascota.setId(newId);
                }
                // Mantener solo últimas 5
                db.ensureLimitFive();

                Toast.makeText(holder.itemView.getContext(), "Rating guardado", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(holder.itemView.getContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
            } finally {
                // db se cierra dentro de los métodos
            }

            // Notificamos cambio visual
            notifyItemChanged(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoto, btnLike;
        TextView tvNombre, tvRating;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            btnLike = itemView.findViewById(R.id.btnLike);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}


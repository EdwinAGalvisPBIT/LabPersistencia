package com.example.myapplab;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritosActivity extends AppCompatActivity {

    private ArrayList<Mascota> mascotasFavoritas;
    private RecyclerView recyclerViewFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        recyclerViewFavoritos = findViewById(R.id.recyclerViewFavoritos);
        recyclerViewFavoritos.setLayoutManager(new LinearLayoutManager(this));

        MascotaDbHelper db = new MascotaDbHelper(this);
        // Trae las últimas 5
        mascotasFavoritas = new ArrayList<>(db.getLastFiveMascotas());

        MascotaAdapter adapter = new MascotaAdapter(mascotasFavoritas);
        recyclerViewFavoritos.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Cierra esta activity
        return true;
    }
}

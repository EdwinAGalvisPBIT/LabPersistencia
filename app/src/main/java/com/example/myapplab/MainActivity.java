package com.example.myapplab;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private RecyclerView recyclerViewMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewMascotas = findViewById(R.id.recyclerViewMascotas);
        recyclerViewMascotas.setLayoutManager(new LinearLayoutManager(this));

        inicializarListaMascotas();

        MascotaAdapter adapter = new MascotaAdapter(mascotas);
        recyclerViewMascotas.setAdapter(adapter);
    }

    private void inicializarListaMascotas() {
        mascotas = new ArrayList<>();
        mascotas.add(new Mascota(R.drawable.allan, "Allan", 5));
        mascotas.add(new Mascota(R.drawable.betto, "Betto", 3));
        mascotas.add(new Mascota(R.drawable.cindy, "Cindy", 2));
        mascotas.add(new Mascota(R.drawable.diego, "Diego", 4));
        mascotas.add(new Mascota(R.drawable.einstein, "Einstein", 1));
        mascotas.add(new Mascota(R.drawable.firulais, "Firulais", 5));
        mascotas.add(new Mascota(R.drawable.gotti, "Gotti", 3));
        mascotas.add(new Mascota(R.drawable.hinno, "Hinno", 2));
        mascotas.add(new Mascota(R.drawable.ivan, "Ivan", 4));
        mascotas.add(new Mascota(R.drawable.juan, "Juan", 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Asegúrate de tener este archivo creado
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favoritos) {
            Intent intent = new Intent(this, FavoritosActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


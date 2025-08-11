package com.example.myapplab;

public class Mascota {
    // id único (0 = no persistida aún)
    private long id;
    private int foto;
    private String nombre;
    private int rating;

    // Constructor con id (usado al leer desde BD)
    public Mascota(long id, int foto, String nombre, int rating) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.rating = rating;
    }

    // Constructor que ya existía (mantener compatibilidad)
    public Mascota(int foto, String nombre, int rating) {
        this(0, foto, nombre, rating);
    }

    // getters y setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void aumentarRating() {
        this.rating++;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "id=" + id +
                ", foto=" + foto +
                ", nombre='" + nombre + '\'' +
                ", rating=" + rating +
                '}';
    }
}


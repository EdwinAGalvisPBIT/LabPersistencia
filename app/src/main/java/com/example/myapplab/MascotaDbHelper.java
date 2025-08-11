package com.example.myapplab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MascotaDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mascotas.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_MASCOTA = "mascota";
    public static final String COL_ID = "_id";
    public static final String COL_FOTO = "foto";
    public static final String COL_NOMBRE = "nombre";
    public static final String COL_RATING = "rating";

    public MascotaDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_MASCOTA + " (" +
                        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_FOTO + " INTEGER, " +
                        COL_NOMBRE + " TEXT, " +
                        COL_RATING + " INTEGER" +
                        ");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Para el laboratorio está bien borrar y recrear.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MASCOTA);
        onCreate(db);
    }

    // Inserta y retorna el id generado
    public long insertMascota(Mascota m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_FOTO, m.getFoto());
        cv.put(COL_NOMBRE, m.getNombre());
        cv.put(COL_RATING, m.getRating());
        long id = db.insert(TABLE_MASCOTA, null, cv);
        db.close();
        return id;
    }

    // Actualiza por id
    public int updateMascota(Mascota m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_FOTO, m.getFoto());
        cv.put(COL_NOMBRE, m.getNombre());
        cv.put(COL_RATING, m.getRating());
        int rows = db.update(TABLE_MASCOTA, cv, COL_ID + "=?", new String[]{String.valueOf(m.getId())});
        db.close();
        return rows;
    }

    // Busca por nombre+foto (útil para evitar duplicados)
    public Mascota findMascotaByNombreAndFoto(String nombre, int foto) {
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT " + COL_ID + ", " + COL_FOTO + ", " + COL_NOMBRE + ", " + COL_RATING +
                " FROM " + TABLE_MASCOTA + " WHERE " + COL_NOMBRE + "=? AND " + COL_FOTO + "=?";
        Cursor c = db.rawQuery(q, new String[]{nombre, String.valueOf(foto)});
        Mascota result = null;
        if (c.moveToFirst()) {
            long id = c.getLong(c.getColumnIndexOrThrow(COL_ID));
            int f = c.getInt(c.getColumnIndexOrThrow(COL_FOTO));
            String n = c.getString(c.getColumnIndexOrThrow(COL_NOMBRE));
            int r = c.getInt(c.getColumnIndexOrThrow(COL_RATING));
            result = new Mascota(id, f, n, r);
        }
        c.close();
        db.close();
        return result;
    }

    // Devuelve las últimas 5 mascotas (order by id desc limit 5)
    public List<Mascota> getLastFiveMascotas() {
        List<Mascota> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT " + COL_ID + ", " + COL_FOTO + ", " + COL_NOMBRE + ", " + COL_RATING +
                " FROM " + TABLE_MASCOTA + " ORDER BY " + COL_ID + " DESC LIMIT 5";
        Cursor c = db.rawQuery(q, null);
        if (c.moveToFirst()) {
            do {
                long id = c.getLong(c.getColumnIndexOrThrow(COL_ID));
                int foto = c.getInt(c.getColumnIndexOrThrow(COL_FOTO));
                String nombre = c.getString(c.getColumnIndexOrThrow(COL_NOMBRE));
                int rating = c.getInt(c.getColumnIndexOrThrow(COL_RATING));
                list.add(new Mascota(id, foto, nombre, rating));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    // Elimina todas las filas que no estén entre las últimas 5 (método simple y efectivo)
    public void ensureLimitFive() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_MASCOTA +
                " WHERE " + COL_ID + " NOT IN (SELECT " + COL_ID + " FROM " + TABLE_MASCOTA +
                " ORDER BY " + COL_ID + " DESC LIMIT 5)";
        db.execSQL(sql);
        db.close();
    }
}

package com.example.ahorcado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BaseDatos extends SQLiteOpenHelper {

    private final static String NAME = "ahorcado.db";
    private final static int VERSION = 1;

    public final static String PET = "Pet";
    private final static String CREATE_PETS = "CREATE TABLE " + PET + " (" +
                                                      " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                      " espaniol TEXT, " +
                                                      " ingles TEXT);";

    public BaseDatos(Context context){
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.v(BaseDatos.class.getName(), "Creando Base de Datos");

        database.execSQL(CREATE_PETS);
    }

    private void doReset(SQLiteDatabase database){
        database.execSQL("DROP TABLE IF EXISTS " + PET);

        onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int from, int to) {
        /*Log.v(DB.class.getName(),"Upgrade DB, new version: " + to  +
                ", deleting data");
        doReset(database);*/
    }
}

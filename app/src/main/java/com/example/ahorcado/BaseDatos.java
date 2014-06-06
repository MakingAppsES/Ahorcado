package com.example.ahorcado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BaseDatos extends SQLiteOpenHelper {

    private final static String NAME = "ahorcado.db";
    private final static int VERSION = 1;
    private final static String ESPANIOL = "espaniol";
    private final static String INGLES = "ingles";
    private final static String DIFICULTAD = "dificultad";
    public final static int B1 = 1;
    public final static int B2 = 2;
    public final static int C1 = 3;

    public final static String VOCABULARIO = "Vocabulario";
    private final static String CREATE_VOCABULARIO = "CREATE TABLE " + VOCABULARIO + " (" +
                                                     " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                     ESPANIOL + " TEXT, " +
                                                     INGLES + " TEXT, " +
                                                     DIFICULTAD +" INTEGER);";

    public BaseDatos(Context context){
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Log.v(BaseDatos.class.getName(), "Creando Base de Datos");

        database.execSQL(CREATE_VOCABULARIO);

        // Base de datos inicial
        // loadB1();
        insertPalabra(database,"Perro","Dog",B1);
        insertPalabra(database,"Casa","House",B1);
        insertPalabra(database,"Gato","Cat",B1);

        // loadB2();
        insertPalabra(database,"Enfoque","Approach",B2);
        insertPalabra(database,"Ansioso","Eager",B2);
        insertPalabra(database,"Importante","Significant",B2);

        // loadC1();
        insertPalabra(database,"Descolorido","Faded",C1);
        insertPalabra(database,"Alacena","Larder",C1);
        insertPalabra(database,"Espolvorear","Sprinkle",C1);
    }

    private void insertPalabra(SQLiteDatabase database, String spa, String eng, int dif){
        database.execSQL("INSERT INTO "+VOCABULARIO+" ("+ESPANIOL+","+INGLES+","+DIFICULTAD+") " +
                         "VALUES ('" + spa + "', '" + eng + "', " + dif + ");");
    }

    private void doReset(SQLiteDatabase database){
        database.execSQL("DROP TABLE IF EXISTS " + VOCABULARIO);

        onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int from, int to) {
        Log.v(BaseDatos.class.getName(), "Actualizando nueva version de la base de datos " + to  +
                                         ", eliminando datos");

        doReset(database);
    }

    public void insertPalabra(Palabra p) {
        SQLiteDatabase database = this.getWritableDatabase();

        /*
        database.execSQL("INSERT INTO Vocabulario (espaniol, ingles, dificultad) VALUES (" +
                         p.getEspaniol() + ", " + p.getIngles() + ", " + p.getDificultad() + ");";
        */

        ContentValues values = new ContentValues();

        values.put(ESPANIOL, p.getEspaniol());
        values.put(INGLES, p.getIngles());
        values.put(DIFICULTAD, p.getDificultad());

        if(database.insert(VOCABULARIO, null , values) == -1){
            database.close();
            throw new RuntimeException("No se ha podido insertar la palabra en la base de datos");
        }

        database.close();
    }

    public Palabra queryPalabraAleatoria() {

        Palabra palabra = null;

        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM " + VOCABULARIO +" ORDER BY RANDOM() LIMIT 1;";

        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){

            palabra = new Palabra(cursor.getString(cursor.getColumnIndex(ESPANIOL)),
                    cursor.getString(cursor.getColumnIndex(INGLES)),
                    cursor.getInt(cursor.getColumnIndex(DIFICULTAD)));
        }

        cursor.close();
        database.close();

        return palabra;
    }

    public Palabra queryPalabraAleatoria(int nivel, boolean acumulativo) {

        Palabra palabra = null;

        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM " + VOCABULARIO +
                " WHERE "+BaseDatos.DIFICULTAD + (acumulativo?"<=":"=") + nivel +
                " ORDER BY RANDOM() LIMIT 1;";

        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){

            palabra = new Palabra(cursor.getString(cursor.getColumnIndex(ESPANIOL)),
                    cursor.getString(cursor.getColumnIndex(INGLES)),
                    cursor.getInt(cursor.getColumnIndex(DIFICULTAD)));
        }

        cursor.close();
        database.close();

        return palabra;
    }

    /* ------------------------------------------------------------------------------------------ */
    /* - Carga de diccionarios de vocabulario a partir de las listas oficiales de Cambridge ----- */
    /* ------------------------------------------------------------------------------------------ */

    private void loadB1() {

    }

    private void loadB2() {

    }

    private void loadC1() {

    }

    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */
}

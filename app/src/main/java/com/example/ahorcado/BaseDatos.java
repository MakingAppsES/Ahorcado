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

    public final static String VOCABULARIO = "Vocabulario";
    private final static String CREATE_VOCABULARIO = "CREATE TABLE " + VOCABULARIO + " (" +
                                                     " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                     " espaniol TEXT, " +
                                                     " ingles TEXT, " +
                                                     " dificultad INTEGER);";

    public BaseDatos(Context context){
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.v(BaseDatos.class.getName(), "Creando Base de Datos");

        database.execSQL(CREATE_VOCABULARIO);

        rellenar();
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

        ContentValues values = new ContentValues();

        values.put("espaniol", p.getEspaniol());
        values.put("ingles", p.getIngles());
        values.put("dificultad", p.getDificultad());

        if(database.insert(VOCABULARIO, null , values) == -1){
            database.close();
            throw new RuntimeException("No se ha podido insertar la palabra en la base de datos");
        }

        database.close();
    }

    public Palabra queryPalabraAleatoria() {

        Palabra palabra = null;

        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + VOCABULARIO +
                       " WHERE _ROWID_ >= (abs(random()) % (SELECT max(_ROWID_) FROM " + VOCABULARIO + "))" +
                       " LIMIT 1;";

        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){

            palabra = new Palabra(cursor.getString(cursor.getColumnIndex("espaniol")),
                                  cursor.getString(cursor.getColumnIndex("ingles")),
                                  cursor.getInt(cursor.getColumnIndex("dificultad")));
        }

        cursor.close();
        database.close();

        return palabra;
    }

    private void rellenar() {
        insertPalabra(new Palabra("take off", "despegar", 2));
        insertPalabra(new Palabra("take off", "quitarse", 2));
        insertPalabra(new Palabra("house", "casa", 1));
        insertPalabra(new Palabra("dog", "perro", 1));
        insertPalabra(new Palabra("cat", "gato", 1));
    }
}

package com.example.ahorcado;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lista de botones
        ArrayList<Button> botones = new ArrayList<Button>();
        // Rellenar la lista de botones
        botones.add((Button) findViewById(R.id.btn_about));
        botones.add((Button) findViewById(R.id.btn_new_game));
        botones.add((Button) findViewById(R.id.btn_settings));
        botones.add((Button) findViewById(R.id.btn_statistics));

        // Ruta de la fuente
        String fontPath = "fonts/FFF_Tusj.ttf";
        // Carga de la fuente
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // Aplicando la fuente a los botones
        for(Button b : botones)
            b.setTypeface(tf);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newGame(View view) {
        startActivity(new Intent(this, GameActivity.class));

        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.pagination);
        mediaPlayer.start();

        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    public void resultados(View view) {
        startActivity(new Intent(this, ResultadosActivity.class));

        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.pagination);
        mediaPlayer.start();

        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    public void opciones(View view) {
        startActivity(new Intent(this, OpcionesActivity.class));

        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.pagination);
        mediaPlayer.start();

        overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
    }

    public void acercaDe(View view) {
        startActivity(new Intent(this, AcercaDeActivity.class));

        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.pagination);
        mediaPlayer.start();

        overridePendingTransition(R.anim.top_in, R.anim.top_out);
    }
}

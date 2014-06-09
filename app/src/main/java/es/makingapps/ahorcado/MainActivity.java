package es.makingapps.ahorcado;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    public static String KEY_SONIDO = "SONIDO";
    public static final String KEY_NIVEL = "nivel";
    public static final String KEY_ACUMULATIVO = "acum";
    public static final String KEY_PET = "FET";
    public static final String KEY_FIRST = "FST";
    public static final String KEY_ADVANCED = "ADV";
    public static final String KEY_WON = "WON";
    public static final String KEY_LOST = "LST";
    public static final String KEY_KEYBOARD = "KBD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(es.makingapps.ahorcado.R.layout.activity_main);

        // Lista de botones
        ArrayList<Button> botones = new ArrayList<Button>();
        // Rellenar la lista de botones
        botones.add((Button) findViewById(es.makingapps.ahorcado.R.id.btn_about));
        botones.add((Button) findViewById(es.makingapps.ahorcado.R.id.btn_new_game));
        botones.add((Button) findViewById(es.makingapps.ahorcado.R.id.btn_settings));
        botones.add((Button) findViewById(es.makingapps.ahorcado.R.id.btn_statistics));

        // Ruta de la fuente
        String fontPath = "fonts/FFF_Tusj.ttf";
        // Carga de la fuente
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // Aplicando la fuente a los botones
        for(Button b : botones)
            b.setTypeface(tf);

        // Aplico la fuente al titulo
        ((TextView) findViewById(es.makingapps.ahorcado.R.id.tv_tittle_main)).setTypeface(tf);

        // Pongo el drawable correspondiente en el boton segun este activado o no el sonido
        Boolean sonidoActivo;
        Button btnSonido = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_sound);
        try {
            sonidoActivo = PreferenceManager.getBoolean(KEY_SONIDO,this);
        } catch (Exception e) {
            Log.e("PreferenceManager",e.toString());
            PreferenceManager.putBoolean(KEY_SONIDO,true,this);
            sonidoActivo = true;
        }
        btnSonido.setBackgroundResource(sonidoActivo ? es.makingapps.ahorcado.R.drawable.btn_sound_on :
                es.makingapps.ahorcado.R.drawable.btn_sound_off);

        // SetUp anuncios
        try {
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(es.makingapps.ahorcado.R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == es.makingapps.ahorcado.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newGame(View view) {
        new DifficultyDialog(this).show();
    }

    public void resultados(View view) {
        startActivity(new Intent(this, ResultadosActivity.class));

        reproducirSonido(es.makingapps.ahorcado.R.raw.pagination, this);

        overridePendingTransition(es.makingapps.ahorcado.R.anim.right_in, es.makingapps.ahorcado.R.anim.right_out);
    }

    public void opciones(View view) {
        startActivity(new Intent(this, OpcionesActivity.class));

        reproducirSonido(es.makingapps.ahorcado.R.raw.pagination, this);

        overridePendingTransition(es.makingapps.ahorcado.R.anim.top_in, es.makingapps.ahorcado.R.anim.top_out);
    }

    public void acercaDe(View view) {
        startActivity(new Intent(this, AcercaDeActivity.class));

        reproducirSonido(es.makingapps.ahorcado.R.raw.pagination, this);

        overridePendingTransition(es.makingapps.ahorcado.R.anim.bottom_in, es.makingapps.ahorcado.R.anim.bottom_out);
    }

    public void toggleSonido(View view) {
        Boolean sonidoActivo;
        Button btnSonido = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_sound);

        try {
            sonidoActivo = PreferenceManager.getBoolean(KEY_SONIDO,this);
        } catch (Exception e) {
            Log.e("PreferenceManager",e.toString());
            PreferenceManager.putBoolean(KEY_SONIDO,true,this);
            sonidoActivo = true;
        }

        btnSonido.setBackgroundResource(sonidoActivo ? es.makingapps.ahorcado.R.drawable.btn_sound_off :
                es.makingapps.ahorcado.R.drawable.btn_sound_on);
        PreferenceManager.putBoolean(KEY_SONIDO, !sonidoActivo, this);
    }

    public static void reproducirSonido(int idSonido, Activity activity) {
        Boolean sonidoActivo;
        try {
            sonidoActivo = PreferenceManager.getBoolean(KEY_SONIDO,activity);
        } catch (Exception e) {
            Log.e("PreferenceManager",e.toString());
            PreferenceManager.putBoolean(KEY_SONIDO,true,activity);
            sonidoActivo = true;
        }

        if( sonidoActivo ){
            MediaPlayer mediaPlayer = MediaPlayer.create(activity.getApplicationContext(), idSonido);
            mediaPlayer.start();
        }
    }
}

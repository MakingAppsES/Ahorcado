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
import android.widget.TextView;


public class ResultadosActivity extends ActionBarActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        int ganadasPet, perdidasPet, ganadasFirst, perdidasFirst, ganadasAdvanced, perdidasAdvanced;

        // Carga de la fuente
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/FFF_Tusj.ttf");

        TextView tv_pet = (TextView) findViewById(R.id.tv_pet_result);
        TextView tv_first = (TextView) findViewById(R.id.tv_first_result);
        TextView tv_advanced = (TextView) findViewById(R.id.tv_advanced_result);

        ((TextView)findViewById(R.id.tv_tittle_statistics)).setTypeface(tf);
        ((TextView)findViewById(R.id.tv_pet)).setTypeface(tf);
        ((TextView)findViewById(R.id.tv_first)).setTypeface(tf);
        ((TextView)findViewById(R.id.tv_advanced)).setTypeface(tf);
        tv_pet.setTypeface(tf);
        tv_first.setTypeface(tf);
        tv_advanced.setTypeface(tf);
        ((Button)findViewById(R.id.btn_delete_results)).setTypeface(tf);

        // OnClick para resetear los resultados
        ((Button)findViewById(R.id.btn_delete_results)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.putInt(MainActivity.KEY_PET+MainActivity.KEY_WON, 0, ResultadosActivity.this);
                PreferenceManager.putInt(MainActivity.KEY_PET+MainActivity.KEY_LOST, 0, ResultadosActivity.this);
                PreferenceManager.putInt(MainActivity.KEY_FIRST+MainActivity.KEY_WON, 0, ResultadosActivity.this);
                PreferenceManager.putInt(MainActivity.KEY_FIRST+MainActivity.KEY_LOST, 0, ResultadosActivity.this);
                PreferenceManager.putInt(MainActivity.KEY_ADVANCED+MainActivity.KEY_WON, 0, ResultadosActivity.this);
                PreferenceManager.putInt(MainActivity.KEY_ADVANCED + MainActivity.KEY_LOST, 0, ResultadosActivity.this);
                ResultadosActivity.this.onBackPressed();
            }
        });

        try { // Cogemos el numero de partidas ganadas del PET
            ganadasPet = PreferenceManager.getInt(MainActivity.KEY_PET+MainActivity.KEY_WON, this);
        } catch (Exception e) {
            ganadasPet = 0;
        }
        try { // Cogemos el numero de partidas perdidas del PET
            perdidasPet = PreferenceManager.getInt(MainActivity.KEY_PET+MainActivity.KEY_LOST, this);
        } catch (Exception e) {
           perdidasPet = 0;
        }

        try { // Cogemos el numero de partidas ganadas del FIRST
            ganadasFirst = PreferenceManager.getInt(MainActivity.KEY_FIRST+MainActivity.KEY_WON, this);
        } catch (Exception e) {
            ganadasFirst = 0;
        }
        try { // Cogemos el numero de partidas perdidas del FIRST
            perdidasFirst = PreferenceManager.getInt(MainActivity.KEY_FIRST+MainActivity.KEY_LOST, this);
        } catch (Exception e) {
            perdidasFirst = 0;
        }

        try { // Cogemos el numero de partidas ganadas del ADVANCED
            ganadasAdvanced = PreferenceManager.getInt(MainActivity.KEY_ADVANCED+MainActivity.KEY_WON, this);
        } catch (Exception e) {
            ganadasAdvanced = 0;
        }
        try { // Cogemos el numero de partidas perdidas del ADVANCED
            perdidasAdvanced = PreferenceManager.getInt(MainActivity.KEY_ADVANCED+MainActivity.KEY_LOST, this);
        } catch (Exception e) {
            perdidasAdvanced = 0;
        }

        tv_pet.setText("Ganadas: "+ganadasPet+"\nPerdidas: "+perdidasPet);
        tv_first.setText("Ganadas: "+ganadasFirst+"\nPerdidas: "+perdidasFirst);
        tv_advanced.setText("Ganadas: "+ganadasAdvanced+"\nPerdidas: "+perdidasAdvanced);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.resultados, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        MainActivity.reproducirSonido(R.raw.pagination, this);

        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }
}

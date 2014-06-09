package es.makingapps.ahorcado;

import android.graphics.Typeface;
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
        setContentView(es.makingapps.ahorcado.R.layout.activity_resultados);
        int ganadasPet, perdidasPet, ganadasFirst, perdidasFirst, ganadasAdvanced, perdidasAdvanced;
        float porcentajePet, porcentajeFirst, porcentajeAdvance;

        // Carga de la fuente
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/FFF_Tusj.ttf");

        final TextView tv_pet = (TextView) findViewById(es.makingapps.ahorcado.R.id.tv_pet_result);
        final TextView tv_first = (TextView) findViewById(es.makingapps.ahorcado.R.id.tv_first_result);
        final TextView tv_advanced = (TextView) findViewById(es.makingapps.ahorcado.R.id.tv_advanced_result);

        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_tittle_statistics)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_pet)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_first)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_advanced)).setTypeface(tf);
        tv_pet.setTypeface(tf);
        tv_first.setTypeface(tf);
        tv_advanced.setTypeface(tf);
        ((Button)findViewById(es.makingapps.ahorcado.R.id.btn_delete_results)).setTypeface(tf);

        // OnClick para resetear los resultados
        ((Button)findViewById(es.makingapps.ahorcado.R.id.btn_delete_results)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.putInt(MainActivity.KEY_PET+MainActivity.KEY_WON, 0, ResultadosActivity.this);
                PreferenceManager.putInt(MainActivity.KEY_PET+MainActivity.KEY_LOST, 0, ResultadosActivity.this);
                PreferenceManager.putInt(MainActivity.KEY_FIRST+MainActivity.KEY_WON, 0, ResultadosActivity.this);
                PreferenceManager.putInt(MainActivity.KEY_FIRST+MainActivity.KEY_LOST, 0, ResultadosActivity.this);
                PreferenceManager.putInt(MainActivity.KEY_ADVANCED+MainActivity.KEY_WON, 0, ResultadosActivity.this);
                PreferenceManager.putInt(MainActivity.KEY_ADVANCED + MainActivity.KEY_LOST, 0, ResultadosActivity.this);

                tv_pet.setText("Ganadas: "+0+"\nPerdidas: "+0);
                tv_first.setText("Ganadas: "+0+"\nPerdidas: "+0);
                tv_advanced.setText("Ganadas: "+0+"\nPerdidas: "+0);
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

        try {
            porcentajePet = ganadasPet / (ganadasPet + perdidasPet);
        } catch (Exception e) {
            porcentajePet = 0;
        }
        try {
            porcentajeFirst = ganadasFirst / (ganadasFirst + perdidasFirst);
        } catch (Exception e) {
            porcentajeFirst = 0;
        }
        try {
            porcentajeAdvance = ganadasAdvanced / (ganadasAdvanced + perdidasAdvanced);
        } catch (Exception e) {
            porcentajeAdvance = 0;
        }

        tv_pet.setText("Ganadas: "+ganadasPet+"\nPerdidas: "+perdidasPet+"\nPorcentaje de aciertos: "+ porcentajePet +"%");
        tv_first.setText("Ganadas: "+ganadasFirst+"\nPerdidas: "+perdidasFirst+"\nPorcentaje de aciertos: "+ porcentajeFirst +"%");
        tv_advanced.setText("Ganadas: "+ganadasAdvanced+"\nPerdidas: "+perdidasAdvanced+"\nPorcentaje de aciertos: "+ porcentajeAdvance +"%");
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
        if (id == es.makingapps.ahorcado.R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        MainActivity.reproducirSonido(es.makingapps.ahorcado.R.raw.pagination, this);

        overridePendingTransition(es.makingapps.ahorcado.R.anim.left_in, es.makingapps.ahorcado.R.anim.left_out);
    }
}

package es.makingapps.ahorcado;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class OpcionesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(es.makingapps.ahorcado.R.layout.activity_opciones);

        // Carga de la fuente
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/FFF_Tusj.ttf");

        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_tittle_options)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_keyboard)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.btn_keyboard)).setTypeface(tf);

        Boolean querty = false;
        try {
            querty = PreferenceManager.getBoolean(MainActivity.KEY_KEYBOARD, this);
        } catch (Exception e) {
            PreferenceManager.putBoolean(MainActivity.KEY_KEYBOARD,false,this);
            querty = false;
        }

        cambiarTextViewKeyboard(querty);

        findViewById(es.makingapps.ahorcado.R.id.btn_keyboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleKeyboard();
            }
        });
    }

    private void toggleKeyboard() {
        Boolean querty;
        try {
            querty = ! PreferenceManager.getBoolean(MainActivity.KEY_KEYBOARD, this);
            PreferenceManager.putBoolean(
                    MainActivity.KEY_KEYBOARD,
                    querty,
                    OpcionesActivity.this
            );
        } catch (Exception e) {
            PreferenceManager.putBoolean(MainActivity.KEY_KEYBOARD,false,this);
            querty = false;
        }

        cambiarTextViewKeyboard(querty);
    }

    private void cambiarTextViewKeyboard(boolean querty) {
        if(querty){
            ((TextView)findViewById(es.makingapps.ahorcado.R.id.btn_keyboard)).setTextColor(
                    getResources().getColor(es.makingapps.ahorcado.R.color.darkgreen)
            );
            ((TextView)findViewById(es.makingapps.ahorcado.R.id.btn_keyboard)).setText("Sí");
        }
        else {
            ((TextView)findViewById(es.makingapps.ahorcado.R.id.btn_keyboard)).setTextColor(
                    getResources().getColor(es.makingapps.ahorcado.R.color.darkred)
            );
            ((TextView)findViewById(es.makingapps.ahorcado.R.id.btn_keyboard)).setText("No");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.opciones, menu);
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

        overridePendingTransition(es.makingapps.ahorcado.R.anim.bottom_in, es.makingapps.ahorcado.R.anim.bottom_out);
    }
}

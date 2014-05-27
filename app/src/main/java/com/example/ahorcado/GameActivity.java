package com.example.ahorcado;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends ActionBarActivity {
    View.OnClickListener clickListenerLetras;
    ArrayList<TextView> botonesLetras;
    TextView palabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Lista de botones
        botonesLetras = new ArrayList<TextView>();
        // Rellenar la lista de botonesLetras
        botonesLetras.add((TextView) findViewById(R.id.txt_a));
        botonesLetras.add((TextView) findViewById(R.id.txt_b));
        botonesLetras.add((TextView) findViewById(R.id.txt_c));
        botonesLetras.add((TextView) findViewById(R.id.txt_d));
        botonesLetras.add((TextView) findViewById(R.id.txt_e));
        botonesLetras.add((TextView) findViewById(R.id.txt_f));
        botonesLetras.add((TextView) findViewById(R.id.txt_g));
        botonesLetras.add((TextView) findViewById(R.id.txt_h));
        botonesLetras.add((TextView) findViewById(R.id.txt_i));
        botonesLetras.add((TextView) findViewById(R.id.txt_j));
        botonesLetras.add((TextView) findViewById(R.id.txt_k));
        botonesLetras.add((TextView) findViewById(R.id.txt_l));
        botonesLetras.add((TextView) findViewById(R.id.txt_m));
        botonesLetras.add((TextView) findViewById(R.id.txt_n));
        botonesLetras.add((TextView) findViewById(R.id.txt_o));
        botonesLetras.add((TextView) findViewById(R.id.txt_p));
        botonesLetras.add((TextView) findViewById(R.id.txt_q));
        botonesLetras.add((TextView) findViewById(R.id.txt_r));
        botonesLetras.add((TextView) findViewById(R.id.txt_s));
        botonesLetras.add((TextView) findViewById(R.id.txt_t));
        botonesLetras.add((TextView) findViewById(R.id.txt_u));
        botonesLetras.add((TextView) findViewById(R.id.txt_v));
        botonesLetras.add((TextView) findViewById(R.id.txt_w));
        botonesLetras.add((TextView) findViewById(R.id.txt_x));
        botonesLetras.add((TextView) findViewById(R.id.txt_y));
        botonesLetras.add((TextView) findViewById(R.id.txt_z));

        // TextView de la palabra:
        palabra = (TextView) findViewById(R.id.txt_palabra);

        // ClickListener para las letras:
        clickListenerLetras = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameActivity.this,((TextView)view).getText(), Toast.LENGTH_LONG).show();
            }
        };

        // Ruta de la fuente
        String fontPath = "fonts/FFF_Tusj.ttf";
        // Carga de la fuente
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // Aplicando la fuente y el tamanio a los botones
        for(TextView b : botonesLetras) {
            b.setTypeface(tf);
            b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
            b.setOnClickListener(clickListenerLetras);
        }
        palabra.setTypeface(tf);

        BaseDatos bd = new BaseDatos(this);
        ((TextView) findViewById(R.id.txt_palabra)).setText(bd.queryPalabraAleatoria().getIngles());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game, menu);

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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));

        overridePendingTransition(R.anim.right_in, R.anim.right_out);

        finish(); // cerramos este Activity
    }
}

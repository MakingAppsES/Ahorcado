package com.example.ahorcado;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GameActivity extends ActionBarActivity {

    private static final int FALLOS = 7;

    private View.OnClickListener clickListenerLetras;
    private ArrayList<TextView> botonesLetras;
    private TextView palabraEspaniol;
    private TextView palabraIngles;
    private Palabra palabraActual;
    private int fallos;
    private GameDialog gameDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        fallos = 0;

        gameDialog = new GameDialog();

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

        // TextView de las palabras
        palabraEspaniol = (TextView) findViewById(R.id.txt_palabra_espaniol);
        palabraIngles = (TextView) findViewById(R.id.txt_palabra_ingles);

        // ClickListener para las letras:
        clickListenerLetras = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(GameActivity.this,((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                comprobarLetra((TextView) view);
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
        palabraEspaniol.setTypeface(tf);
        palabraIngles.setTypeface(tf);

        // Inicializacion de la base de datos
        BaseDatos bd = new BaseDatos(this);

        palabraActual = bd.queryPalabraAleatoria();

        palabraEspaniol.setText(palabraActual.getEspaniol());
        palabraIngles.setText(palabraActual.palabraToGuiones());
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

    public void comprobarLetra(TextView textView) {
        if (palabraActual.getIngles().contains(textView.getText())) {
            textView.setTextColor(Color.GREEN);

            String progreso = palabraIngles.getText().toString();
            String solucion = palabraActual.getIngles();

            String nuevoProgreso = "";
            char letra;
            for (int i = 0; i < solucion.length(); i++) {
                letra = textView.getText().charAt(0);
                nuevoProgreso += (solucion.charAt(i) == letra) ? letra : progreso.charAt(i);
            }

            palabraIngles.setText(nuevoProgreso);

            if (nuevoProgreso.equals(solucion)) {
                // GANA
                System.out.print("entro");
                gameDialog.show(getSupportFragmentManager(), "win");
            }
        }
        else {
            textView.setTextColor(Color.RED);

            fallos++;

            if (fallos == FALLOS) {
                System.out.print("entro");
                // gameDialog.setCancelable(false);
                gameDialog.show(getSupportFragmentManager(), "lose");
            }
        }
    }
}

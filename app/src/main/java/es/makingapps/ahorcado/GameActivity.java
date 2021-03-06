package es.makingapps.ahorcado;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class GameActivity extends ActionBarActivity {

    public static final int MAX_FALLOS = 7;

    private View.OnClickListener clickListenerLetras;
    private ArrayList<TextView> botonesLetras;
    private TextView palabraEspaniol,
                     palabraIngles;
    private String progreso;
    private Palabra palabraActual;
    private int fallos,
                nivelSeleccionado;
    private boolean finDePartida = false; // Evita que pulsemos otras letras si somos rapidos
    private boolean esAcumulativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Boolean querty = false;
        try {
            querty = PreferenceManager.getBoolean(MainActivity.KEY_KEYBOARD, this);
        } catch (Exception e) {
            PreferenceManager.putBoolean(MainActivity.KEY_KEYBOARD,true,this);
            querty = true;
        }
        setContentView(querty ? es.makingapps.ahorcado.R.layout.activity_game_qwerty : es.makingapps.ahorcado.R.layout.activity_game);

        String fontName = "Tinet.ttf";

        Bundle extras = getIntent().getExtras();
        // Obtenemos datos enviados en el intent.
        if (extras != null) {
            nivelSeleccionado = extras.getInt(MainActivity.KEY_NIVEL);
            esAcumulativo = extras.getBoolean(MainActivity.KEY_ACUMULATIVO);
        }else{
            nivelSeleccionado = BaseDatos.B1;
            esAcumulativo = false;
        }

        // Lista de botones
        botonesLetras = new ArrayList<TextView>();
        // Rellenar la lista de botonesLetras
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_a));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_b));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_c));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_d));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_e));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_f));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_g));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_h));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_i));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_j));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_k));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_l));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_m));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_n));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_o));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_p));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_q));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_r));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_s));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_t));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_u));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_v));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_w));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_x));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_y));
        botonesLetras.add((TextView) findViewById(es.makingapps.ahorcado.R.id.txt_z));

        // TextView de las palabras
        palabraEspaniol = (TextView) findViewById(es.makingapps.ahorcado.R.id.txt_palabra_espaniol);
        palabraIngles = (TextView) findViewById(es.makingapps.ahorcado.R.id.txt_palabra_ingles);

        // ClickListener para las letras:
        clickListenerLetras = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(GameActivity.this,((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                ((TextView) view).setOnClickListener(null);

                comprobarLetra((TextView) view);
            }
        };

        // Carga de la fuente
        Typeface tf = TypeFaceProvider.getTypeFace(this,fontName);

        // Aplicando la fuente y el tamanio a los botones
        for(TextView b : botonesLetras) {
            b.setTypeface(tf);
            //b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
            b.setOnClickListener(clickListenerLetras);
        }
        palabraEspaniol.setTypeface(tf);
        palabraIngles.setTypeface(tf);

        nuevoJuego();

        // SetUp anuncios
        try {
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Inicia un nuevo juego y toma una nueva palabra.
     */
    public void nuevoJuego() {
        fallos = 0;
        finDePartida = false;

        for (TextView b : botonesLetras) {
            b.setTextColor(Color.BLACK);
            b.setOnClickListener(clickListenerLetras);
            b.setPaintFlags(b.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        ImageView img_ahorcado = (ImageView) findViewById(es.makingapps.ahorcado.R.id.img_ahorcado);
        img_ahorcado.setImageResource(R.drawable.ahorcado_0);

        BaseDatos bd = new BaseDatos(this);
        palabraActual = bd.queryPalabraAleatoria(nivelSeleccionado, esAcumulativo);

        palabraEspaniol.setText(palabraActual.getEspaniol());

        progreso = "";
        boolean parentesis = false;
        for(int i = 0; i<palabraActual.getIngles().length(); i++) {
            if(parentesis){
                progreso += palabraActual.getIngles().charAt(i);
            }
            else {
//                if (palabraActual.getIngles().charAt(i) == ' ')
//                    progreso += ' ';
//                else if (palabraActual.getIngles().charAt(i) == '-')
//                    progreso += '-';
//                else if (palabraActual.getIngles().charAt(i) == '/')
//                    progreso += '/';
//                else if (palabraActual.getIngles().charAt(i) == '(') {
//                    progreso += '(';
//                    parentesis = true;
//                }
//                else if (palabraActual.getIngles().charAt(i) == ')') {
//                    progreso += ')';
//                    parentesis = false;
//                }
//                else
//                    progreso += '_';
                if(Character.isLowerCase(palabraActual.getIngles().charAt(i)))
                    progreso += '_';
                else if (palabraActual.getIngles().charAt(i) == '(') {
                    progreso += '(';
                    parentesis = true;
                }
                else if (palabraActual.getIngles().charAt(i) == ')') {
                    progreso += ')';
                    parentesis = false;
                }
                else
                    progreso += palabraActual.getIngles().charAt(i);
            }
        }

        palabraIngles.setText( progreso );
    }

    public Palabra getPalabraActual() {
        return palabraActual;
    }
    public int getFallosActuales() {
        return fallos;
    }
    public int getNivelSeleccionado() {
        return nivelSeleccionado;
    }
    public boolean getEsAcumuladivo() {
        return esAcumulativo;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.game, menu);
        //new PauseDialog(this).show();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_MENU ) {
            new PauseDialog(this).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == es.makingapps.ahorcado.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new PauseDialog(this).show();
    }

    public void volverAtras() {
        super.onBackPressed();

        MainActivity.reproducirSonido(es.makingapps.ahorcado.R.raw.pagination, this);

        overridePendingTransition(es.makingapps.ahorcado.R.anim.right_in, es.makingapps.ahorcado.R.anim.right_out);
    }

    public void comprobarLetra(TextView textView) {
        if (!finDePartida) {
            if (palabraActual.getIngles().contains(textView.getText())) {
                textView.setTextColor(Color.GREEN);

                String solucion = palabraActual.getIngles();
                String nuevoProgreso = "";
                char letra;
                for (int i = 0; i < progreso.length(); i++) {
                    letra = textView.getText().charAt(0);
                    nuevoProgreso += (solucion.charAt(i) == letra) ? letra : progreso.charAt(i);
                }

                progreso = nuevoProgreso;
                MainActivity.reproducirSonido(es.makingapps.ahorcado.R.raw.acierto, this);

                if (nuevoProgreso.equals(solucion)) {
                    // GANA
                    finDePartida = true;
                    new GameDialog(this, "¡Has acertado!", false).show();
                    almacenarEstadisticas(MainActivity.KEY_WON);
                }

                // visualizacion del proceso
                palabraIngles.setText( progreso );
            } else {
                textView.setTextColor(Color.RED);
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                fallos++;

                ImageView img_ahorcado = (ImageView) findViewById(es.makingapps.ahorcado.R.id.img_ahorcado);
                int id_imagen_fallo = getResources().getIdentifier("es.makingapps.ahorcado:drawable/ahorcado_" + fallos, null, null);
                img_ahorcado.setImageResource(id_imagen_fallo);

                MainActivity.reproducirSonido(es.makingapps.ahorcado.R.raw.error, this);

                if (fallos == MAX_FALLOS) {
                    finDePartida = true;
                    new GameDialog(this, "¡Ooops!", false).show();
                    almacenarEstadisticas(MainActivity.KEY_LOST);
                }
            }
        }
    }

    /* ------------------------------------------------------------------------------------------ */

    /**
     * @deprecated Ya no se utiliza porque se ha modificado la fuente para que las barras bajas
     *             sean m&aacute;s peque&ntilde;as.
     */
    @Deprecated
    private String visualizar(String s) {

        String resultado = "";

        for (int i = 0; i < s.length()-1; i++) {
            resultado += s.charAt(i) + " ";
        }
        resultado += s.charAt(s.length()-1);

        return resultado;
    }

    /**
     * Almacena el resultado para la contabilizacion de las estadisticas.
     * @param resultado <tt>String</tt> que puede ser <tt>MainActivity.KEY_WON</tt> o
     *                  <tt>MainActivity.KEY_LOST</tt>, para indicar si se ha ganado o
     *                  perdido la partida
     */
    private void almacenarEstadisticas(String resultado){
        int nivel = palabraActual.getDificultad();
        String str_nivel = "";
        switch (nivel){
            case BaseDatos.B1: str_nivel = MainActivity.KEY_PET; break;
            case BaseDatos.B2: str_nivel = MainActivity.KEY_FIRST; break;
            case BaseDatos.C1: str_nivel = MainActivity.KEY_ADVANCED; break;
        }

        int contador;
        try {
            contador = PreferenceManager.getInt(str_nivel+resultado, this);
        } catch (Exception e) {
            contador = 0;
        }
        PreferenceManager.putInt(str_nivel+resultado,contador+1,this);
        almacenarFallos();
    }

    /**
     * Actualiza los fallos de la palabra actual en la base de datos.
     */
    private void almacenarFallos() {
        BaseDatos bd = new BaseDatos(this);
//        Log.i("FALLOS","Nmero de fallos final: "+fallos);
        bd.updatePalabra(palabraActual.getId(),fallos);

        bd.close();
    }
}

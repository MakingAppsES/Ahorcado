package es.makingapps.ahorcado;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DifficultyDialog extends ClearDialog {
    Activity activity;

    public DifficultyDialog(Activity context) {
        super(context, true);
        activity = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(es.makingapps.ahorcado.R.layout.dialog_difficulty);

        String fontName = "Tinet.ttf";
        Typeface tf = TypeFaceProvider.getTypeFace(activity,fontName);

        TextView tv_difficulty = (TextView) findViewById(es.makingapps.ahorcado.R.id.tv_difficulty);
        Button btn_b1          = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_b1);
        Button btn_b2_excl     = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_b2_excl);
//        Button btn_b2_acum     = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_b2_acum);
        Button btn_c1_excl     = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_c1_excl);
//        Button btn_c1_acum     = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_c1_acum);

        tv_difficulty.setTypeface(tf);
        btn_b1.setTypeface(tf);
        btn_b2_excl.setTypeface(tf);
//        btn_b2_acum.setTypeface(tf);
        btn_c1_excl.setTypeface(tf);
//        btn_c1_acum.setTypeface(tf);

        btn_b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DifficultyDialog.this.dismiss();
                iniciarPartida(BaseDatos.B1, false);
            }
        });

        btn_b2_excl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DifficultyDialog.this.dismiss();
                iniciarPartida(BaseDatos.B2, false);
            }
        });

//        btn_b2_acum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DifficultyDialog.this.dismiss();
//                iniciarPartida(BaseDatos.B2, true);
//            }
//        });

        btn_c1_excl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DifficultyDialog.this.dismiss();
                iniciarPartida(BaseDatos.C1, false);
            }
        });

//        btn_c1_acum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DifficultyDialog.this.dismiss();
//                iniciarPartida(BaseDatos.C1, true);
//            }
//        });
    }

    private void iniciarPartida(int nivel, boolean acumulativo) {
        Intent intent = new Intent(activity, GameActivity.class);
        intent.putExtra(MainActivity.KEY_NIVEL, nivel);
        intent.putExtra(MainActivity.KEY_ACUMULATIVO, acumulativo);

        activity.startActivity(intent);

        MainActivity.reproducirSonido(es.makingapps.ahorcado.R.raw.pagination, activity);

        activity.overridePendingTransition(es.makingapps.ahorcado.R.anim.left_in, es.makingapps.ahorcado.R.anim.left_out);
    }
}

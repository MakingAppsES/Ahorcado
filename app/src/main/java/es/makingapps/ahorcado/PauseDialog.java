package es.makingapps.ahorcado;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PauseDialog extends ClearDialog {
    Activity activity;

    public PauseDialog(Activity context) {
        super(context, false);
        activity = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(es.makingapps.ahorcado.R.layout.dialog_pause);

        String fontPath = "fonts/FFF_Tusj.ttf";
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), fontPath);

        TextView tv_pause   = (TextView) findViewById(es.makingapps.ahorcado.R.id.tv_pause);
        Button btn_continue = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_continue);
        Button btn_again    = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_again);
        Button btn_menu     = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_menu);

        tv_pause.setTypeface(tf);
        btn_continue.setTypeface(tf);
        btn_again.setTypeface(tf);
        btn_menu.setTypeface(tf);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PauseDialog.this.dismiss();
            }
        });

        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PauseDialog.this.dismiss();
                Intent intent = new Intent(getContext(), GameActivity.class);
                intent.putExtra(MainActivity.KEY_NIVEL, ((GameActivity)activity).getNivelSeleccionado());
                intent.putExtra(MainActivity.KEY_ACUMULATIVO, ((GameActivity)activity).getEsAcumuladivo());

                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                activity.startActivity(intent);
                activity.overridePendingTransition(0,0);
                activity.finish();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PauseDialog.this.dismiss();
                ((GameActivity)activity).volverAtras();
            }
        });
    }
}

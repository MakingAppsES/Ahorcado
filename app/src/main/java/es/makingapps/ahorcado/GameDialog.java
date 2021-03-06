package es.makingapps.ahorcado;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDialog extends ClearDialog {
    private String tittle;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(es.makingapps.ahorcado.R.layout.dialog_game);

        String fontName = "Tinet.ttf";
        Typeface tf = TypeFaceProvider.getTypeFace(activity,fontName);

        TextView tv_tittle = (TextView) findViewById(es.makingapps.ahorcado.R.id.tv_tittle);
        TextView tv_word = (TextView) findViewById(es.makingapps.ahorcado.R.id.tv_word);
        Button btn_again = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_again);
        Button btn_menu = (Button) findViewById(es.makingapps.ahorcado.R.id.btn_menu);
        TextView btn_wr = (TextView) findViewById(es.makingapps.ahorcado.R.id.txt_wr);

        tv_tittle.setTypeface(tf);
        tv_word.setTypeface(tf);
        btn_again.setTypeface(tf);
        btn_menu.setTypeface(tf);
        btn_wr.setTypeface(tf);

        tv_tittle.setText(tittle);
        tv_word.setText((((GameActivity) activity).getPalabraActual()).getIngles());

        tv_word.setTextColor((((GameActivity) activity)).getFallosActuales()==GameActivity.MAX_FALLOS ?
                activity.getResources().getColor(es.makingapps.ahorcado.R.color.darkred) :
                activity.getResources().getColor(es.makingapps.ahorcado.R.color.darkgreen));

        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//            Intent intent = new Intent(getContext(), GameActivity.class);
//            intent.putExtra(MainActivity.KEY_NIVEL, ((GameActivity)activity).getNivelSeleccionado());
//            intent.putExtra(MainActivity.KEY_ACUMULATIVO, ((GameActivity)activity).getEsAcumuladivo());
//
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//
//            GameDialog.this.dismiss();
//            activity.startActivity(intent);
//            activity.overridePendingTransition(0,0);
//            activity.finish();

                ((GameActivity)activity).nuevoJuego();
                GameDialog.this.dismiss();
            }
        });
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                activity.onBackPressed();
                GameDialog.this.dismiss();
                ((GameActivity)activity).volverAtras();
            }
        });

        btn_wr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Palabra p = ((GameActivity) activity).getPalabraActual();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.wordreference.com/es/en/translation.asp?spen=" + p.getEspaniol()));

            activity.startActivity(intent);
            }
        });

    }

    public GameDialog(Activity context, String tittle) {
        super(context);
        activity = context;
        this.tittle = tittle;
    }

    public GameDialog(Activity context, String tittle, boolean cancelable) {
        super(context, cancelable);
        activity = context;
        this.tittle = tittle;
    }
}

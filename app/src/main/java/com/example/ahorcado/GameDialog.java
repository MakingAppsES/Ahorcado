package com.example.ahorcado;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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
        setContentView(R.layout.dialog_game);

        String fontPath = "fonts/FFF_Tusj.ttf";
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), fontPath);

        TextView tv_tittle = (TextView) findViewById(R.id.tv_tittle);
        Button btn_again = (Button) findViewById(R.id.btn_again);
        Button btn_menu = (Button) findViewById(R.id.btn_menu);

        tv_tittle.setTypeface(tf);
        btn_again.setTypeface(tf);
        btn_menu.setTypeface(tf);

        tv_tittle.setText(tittle);

        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(getContext(), GameActivity.class));
                activity.finish();
                GameDialog.this.dismiss();
            }
        });
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
                activity.overridePendingTransition(R.anim.right_in, R.anim.right_out);
                GameDialog.this.dismiss();
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

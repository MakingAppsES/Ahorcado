package com.example.ahorcado;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class GameDialog extends Dialog {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_game);

        findViewById(R.id.btn_nada).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Naaaaaada", Toast.LENGTH_SHORT).show();
                GameDialog.this.dismiss();
            }
        });
    }

    public GameDialog(Context context) {
        super(context, R.style.CustomDialogStyle);
    }

    public GameDialog(Context context, boolean cancelable) {
        super(context, R.style.CustomDialogStyle);
        this.setCancelable(cancelable);
    }

    public GameDialog(Context context, int theme) {
        super(context, theme);
    }
}

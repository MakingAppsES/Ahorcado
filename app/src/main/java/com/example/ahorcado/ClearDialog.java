package com.example.ahorcado;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

/**
 * Dialog personalizado que se caracteriza por no tener ning&uacute;n tipo de decoraci&oacute;n.
 */
public class ClearDialog extends Dialog {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public ClearDialog(Context context) {
        super(context, R.style.CustomDialogStyle);
    }

    public ClearDialog(Context context, boolean cancelable) {
        super(context, R.style.CustomDialogStyle);
        this.setCancelable(cancelable);
    }
}

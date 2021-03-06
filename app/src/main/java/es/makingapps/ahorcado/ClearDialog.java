package es.makingapps.ahorcado;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public ClearDialog(Context context) {
        super(context, es.makingapps.ahorcado.R.style.CustomDialogStyle);
    }

    public ClearDialog(Context context, boolean cancelable) {
        super(context, es.makingapps.ahorcado.R.style.CustomDialogStyle);
        this.setCancelable(cancelable);
    }
}

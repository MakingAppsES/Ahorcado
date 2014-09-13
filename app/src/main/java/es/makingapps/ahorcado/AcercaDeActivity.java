package es.makingapps.ahorcado;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class AcercaDeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(es.makingapps.ahorcado.R.layout.activity_acerca_de);

        String fontName = PreferenceManager.getString(MainActivity.KEY_FONT, this);
        if (fontName == null) {
            PreferenceManager.putString(MainActivity.KEY_FONT,"FFF_Tusj.ttf",this);
            fontName = "FFF_Tusj.ttf";
        }
        Typeface tf = TypeFaceProvider.getTypeFace(this,fontName);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_tittle_about)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_description)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_developers)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_github)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_github_antorof)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_github_carranza)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_github_url)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_github_antorof_url)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_github_carranza_url)).setTypeface(tf);
        ((TextView)findViewById(es.makingapps.ahorcado.R.id.tv_version)).setTypeface(tf);

        View.OnClickListener linkClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dir_url = "";
                switch (view.getId()){
                    case es.makingapps.ahorcado.R.id.tv_github_url:
                            dir_url = "https://github.com/MakingAppsES/Ahorcado";
                        break;
                    case es.makingapps.ahorcado.R.id.tv_github_antorof_url:
                        dir_url = "https://github.com/antorof";
                        break;
                    case es.makingapps.ahorcado.R.id.tv_github_carranza_url:
                        dir_url = "https://github.com/Carranza";
                        break;
                }
                Uri uri = Uri.parse(dir_url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                AcercaDeActivity.this.startActivity(intent);
            }
        };

        findViewById(es.makingapps.ahorcado.R.id.tv_github_url).setOnClickListener(linkClickListener);
        findViewById(es.makingapps.ahorcado.R.id.tv_github_antorof_url).setOnClickListener(linkClickListener);
        findViewById(es.makingapps.ahorcado.R.id.tv_github_carranza_url).setOnClickListener(linkClickListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(es.makingapps.ahorcado.R.menu.acerca_de, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == es.makingapps.ahorcado.R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        MainActivity.reproducirSonido(es.makingapps.ahorcado.R.raw.pagination, this);

        overridePendingTransition(es.makingapps.ahorcado.R.anim.top_in, es.makingapps.ahorcado.R.anim.top_out);
    }
}

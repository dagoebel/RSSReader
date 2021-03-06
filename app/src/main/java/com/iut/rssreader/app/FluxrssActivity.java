package com.iut.rssreader.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by corentin on 21/04/14.
 */
public class FluxrssActivity extends Activity {

    public final static String TAG = "ActionBarActivity";

    private MenuItem itemAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fluxrss);


        /* ============================================
            Declaration des elements du layout
        ============================================= */


    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.d(TAG, String.valueOf(extras.getInt("IDSELECTED")));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        itemAccueil = (MenuItem) findViewById(R.id.action_accueil);

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accueil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_ajout_categorie:
                //do something
                Intent intentAccueil = new Intent(FluxrssActivity.this, MainActivity.class);
                startActivity(intentAccueil);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

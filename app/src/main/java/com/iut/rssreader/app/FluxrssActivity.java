package com.iut.rssreader.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by corentin on 21/04/14.
 */
public class FluxrssActivity extends Activity {

    public final static String TAG = "ActionBarActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fluxrss);


        /* ============================================
            Declaration des elements du layout
        ============================================= */

        // declaration du button Categorie
        final Button btnFlux = (Button) findViewById(R.id.btnFlux);
        btnFlux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // au clic on lance CategActivity
                Intent intent = new Intent(FluxrssActivity.this, GestionfluxrssActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.d(TAG, String.valueOf(extras.getInt("IDSELECTED")));
        }
    }
}

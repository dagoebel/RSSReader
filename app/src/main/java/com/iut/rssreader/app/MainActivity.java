package com.iut.rssreader.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.content.Intent;
import android.widget.*;


public class MainActivity extends ActionBarActivity {

    public final static String TAG = "ActionBarActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* ============================================
            Declaration des elements du layout
        ============================================= */

        // declaration du button Categorie
        final Button btnCategorie = (Button) findViewById(R.id.btnCategorie);
        btnCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // au clic on lance CategActivity
                Intent intent = new Intent(MainActivity.this, CategActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

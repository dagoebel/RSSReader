package com.iut.rssreader.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import sqlite.Categorie;
import sqlite.CategoriesBdd;
import java.util.*;

/**
 * Created by corentin on 21/04/14.
 */
public class GestionfluxrssActivity extends ActionBarActivity {
    public final static String TAG = "ActionBarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionfluxrss);

        CategoriesBdd categoriesBdd = new CategoriesBdd(this);

        /* ============================================
            Gestion des données de la bdd
         ============================================ */
        categoriesBdd.open();
        // List<String> nomsCateg = categoriesBdd.getNoms();
        final List<Categorie> categories = categoriesBdd.getCategories();
        categoriesBdd.close();


         /* ============================================
            Declaration des elements du layout
        ============================================= */

        // declaration du spinner Categorie
        final Spinner spinnerCategorie = (Spinner) findViewById(R.id.spinnerCateg);

        final ArrayAdapter<Categorie> adapterCateg = new ArrayAdapter<Categorie>(this, android.R.layout.simple_list_item_1, categories);
        adapterCateg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorie.setAdapter(adapterCateg);
        /*
        ArrayAdapter<String> adapterCateg = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomsCateg);
        adapterCateg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorie.setAdapter(adapterCateg);
        */


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

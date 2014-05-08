package com.iut.rssreader.app;

import android.content.Intent;
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
public class AddfluxrssActivity extends ActionBarActivity {
    public final static String TAG = "ActionBarActivity";

    private MenuItem itemAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfluxrss);

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
                Intent intentAccueil = new Intent(AddfluxrssActivity.this, MainActivity.class);
                startActivity(intentAccueil);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

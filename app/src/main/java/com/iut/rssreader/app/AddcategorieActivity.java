package com.iut.rssreader.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import sqlite.Categorie;
import sqlite.CategoriesBdd;

/**
 * Created by barre on 08/05/2014.
 */
public class AddcategorieActivity  extends ActionBarActivity {
    public final static String TAG = "ActionBarActivity";

    private MenuItem itemAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcateg);

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
        TextView nomCategorie = (TextView) findViewById(R.id.txtNomCateg);
        TextView descriptionCategorie = (TextView) findViewById(R.id.txtDescriptionCateg);


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
                Intent intentAccueil = new Intent(AddcategorieActivity.this, MainActivity.class);
                startActivity(intentAccueil);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

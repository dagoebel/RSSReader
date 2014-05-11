package com.iut.rssreader.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
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

        final CategoriesBdd categoriesBdd = new CategoriesBdd(this);

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
        final TextView nomCategorie = (TextView) findViewById(R.id.txtNomCateg);
        final TextView descriptionCategorie = (TextView) findViewById(R.id.txtDescriptionCateg);


        Button btnAddCategorie = (Button) findViewById(R.id.btnAjout);
        btnAddCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "";
                String nom = nomCategorie.getText().toString();
                String description = descriptionCategorie.getText().toString();

                // Log.d(TAG, "nom saisi : " + nom + "description saisie : " + description);
                if(nom.length() != 0 && description.length() != 0){
                    Categorie newCategorie = new Categorie(nom, description);
                    // Log.d(TAG, "catégorie à ajouter : " + newCategorie.toString());
                    if(newCategorie != null) {
                        categoriesBdd.open();
                        categoriesBdd.insertCategorie(newCategorie);
                        categoriesBdd.close();
                        msg = "catégorie ajoutée !";
                    }
                    else{
                        msg = "impossible d'ajouter une catégorie";
                    }
                }
                else{
                    msg = "saisir tout les champs svp";
                }
                Toast.makeText(AddcategorieActivity.this, msg, Toast.LENGTH_SHORT).show(); // si pas erreur
            }
        });


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

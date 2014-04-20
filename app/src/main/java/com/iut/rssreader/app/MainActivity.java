package com.iut.rssreader.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.content.Intent;
import android.widget.*;

import sqlite.Categorie;
import sqlite.CategoriesBdd;
import java.util.*;
import android.util.*;

public class MainActivity extends ActionBarActivity {

    public final static String TAG = "ActionBarActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Instance de la classe CategoriesBdd
        CategoriesBdd categoriesBdd = new CategoriesBdd(this);

        // création des categories par defaut (utiliser pour le test)
        Categorie categorieAutre = new Categorie("Autre");

        categoriesBdd.open();
        // categoriesBdd.insertCategorie(categorieAutre); // commenter la ligne apres la 1ere execution

        List<Categorie> categories = categoriesBdd.getCategories(); // recupere une liste des categories
        List<String> nomsCateg = categoriesBdd.getNoms();   // recupere une liste de nom des categories

        Categorie categorieFromBdd = categoriesBdd.getCategorieWithNom(categorieAutre.getNom());
        //Si une categorie est retournée
        if(categorieFromBdd != null){
            Log.d(TAG, categorieFromBdd.toString());
        }

        categoriesBdd.close();



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

        // declaration du spinner Categorie
        final Spinner spinnerCategorie = (Spinner) findViewById(R.id.spinnerCateg);
        ArrayAdapter<String> adapterCateg = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomsCateg);
        adapterCateg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorie.setAdapter(adapterCateg);

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

package com.iut.rssreader.app;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.content.Intent;
import android.widget.*;
import sqlite.Categorie;
import sqlite.CategoriesBdd;
import sqlite.FluxRss;

import java.util.*;
import android.util.*;

public class MainActivity extends Activity {

    public final static String TAG = "ActionBarActivity";
    private static List<Categorie> categories;

    private ArrayAdapter<Categorie> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Instance de la classe CategoriesBdd
        CategoriesBdd categoriesBdd = new CategoriesBdd(this);

        /* ==================================================================
            A executer une seul fois (commenter
            a faire: faire une condition pour le faire automatiquement
        ====================================================================
        Categorie categorie1 = new Categorie("Autre", "flux non triés");
        Categorie categorie2 = new Categorie("Test", "categorie de test");

        categoriesBdd.open();
        categoriesBdd.insertCategorie(categorie1);
        categoriesBdd.insertCategorie(categorie2);
        categoriesBdd.close();
        */


        /* recuperation des categories de la bdd */
        categoriesBdd.open();
        categories = categoriesBdd.getCategories(); // recupere une liste des categories
        // Categorie categorieFromBdd = categoriesBdd.getCategorieWithNom(categorieAutre.getNom());
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


        // declaration de la listeView categorie
        final ListView listCategories = (ListView) findViewById(R.id.lstCateg);
        adapter = new ArrayAdapter<Categorie>(this, android.R.layout.simple_list_item_1, categories);
        listCategories.setAdapter(adapter);

        listCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3){
                // erreur : renvois null
                Categorie categSelected = categories.get(position);
                int categIdSelected = categSelected.getId(); // recupere l'id de la categorie selectionné

                Log.d(TAG, String.valueOf(categIdSelected));

                Intent intent = new Intent(MainActivity.this, FluxrssActivity.class);
                intent.putExtra("IDSELECTED", categIdSelected); // passage de l'id de la categorie au layout
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

    public static List<Categorie> getCategories() {
        return categories;
    }

    @Override
    protected void onRestart() {
        adapter.notifyDataSetChanged();
        super.onRestart();
    }
}

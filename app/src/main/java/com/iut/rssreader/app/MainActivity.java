package com.iut.rssreader.app;

import android.app.Activity;
import android.app.Dialog;
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

    private MenuItem itemAjoutCategorie;
    private MenuItem itemAjoutFluxRss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Instance de la classe CategoriesBdd
        final CategoriesBdd categoriesBdd = new CategoriesBdd(this);

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

        // declaration de la listeView categorie
        final ListView listCategories = (ListView) findViewById(R.id.lstCateg);
        adapter = new ArrayAdapter<Categorie>(this, android.R.layout.simple_list_item_1, categories);
        listCategories.setAdapter(adapter);




        listCategories.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long arg3) {

                // recuperation de l'objet
                final Categorie categSelected = categories.get(position);

                Log.d(TAG, String.valueOf(categSelected.getId()));

                // popup suppression / modification
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.categ_dialog);
                dialog.setTitle("catégorie: " + categSelected.getNom()); // a faire: get nom de la categorie

                Button btnModifier = (Button) dialog.findViewById(R.id.dialogModifier);
                btnModifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();

                        /* ===== popup modification ===== */
                        final Dialog editCategDialog = new Dialog(MainActivity.this);
                        editCategDialog.setContentView(R.layout.categ_modifdialog);
                        editCategDialog.setTitle("modification de la catégorie");
                        final EditText nomCateg = (EditText) editCategDialog.findViewById(R.id.txtNomCateg);
                        final EditText descriptionCateg = (EditText) editCategDialog.findViewById(R.id.txtDescriptionCateg);
                        nomCateg.setText(categSelected.getNom()); // a faire
                        descriptionCateg.setText(categSelected.getDescription()); // a faire


                        Button btnModifCateg = (Button) editCategDialog.findViewById(R.id.btnModif);
                        btnModifCateg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d(TAG, "Categorie avant: " + categSelected.toString());
                                categSelected.setNom(nomCateg.getText().toString());
                                categSelected.setDescription(descriptionCateg.getText().toString());
                                Log.d(TAG, "Categorie apres modif: " + categSelected.toString());
                                // update
                                categoriesBdd.open();
                                categoriesBdd.updateCategorie(categSelected.getId(), categSelected);
                                categoriesBdd.close();

                                // a faire: rafraichir les layouts
                                adapter.notifyDataSetChanged();

                                editCategDialog.cancel();
                                Toast.makeText(MainActivity.this, "modification terminé !", Toast.LENGTH_SHORT).show(); // si pas erreur
                            }
                        });
                        editCategDialog.show();
                        /* ===== FIN popup modification ===== */
                    }
                });

                Button btnSupprimmer = (Button) dialog.findViewById(R.id.dialogSupprimmer);
                btnSupprimmer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // suppression
                        // recuperer l'id de la categ
                        categoriesBdd.open();
                        categoriesBdd.removeCategorieWithID(categSelected.getId());
                        // a faire: rafraichir les layouts
                        adapter.notifyDataSetChanged();
                        categoriesBdd.close();
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "suppression terminé !", Toast.LENGTH_SHORT).show(); // si pas erreur
                    }
                });
                dialog.show();
                return false;
            }
        });

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
        itemAjoutCategorie = (MenuItem) findViewById(R.id.action_ajout_categorie);
        itemAjoutFluxRss = (MenuItem) findViewById(R.id.action_ajout_fluxrss);

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
        switch (id){
            case R.id.action_ajout_categorie:
                //do something
                Intent intentAddCategorie = new Intent(MainActivity.this, AddcategorieActivity.class);
                startActivity(intentAddCategorie);
                return true;
            case R.id.action_ajout_fluxrss:
                //do something
                Intent intentAddFluxRss = new Intent(MainActivity.this, AddfluxrssActivity.class);
                startActivity(intentAddFluxRss);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

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

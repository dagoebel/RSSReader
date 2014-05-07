package com.iut.rssreader.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import sqlite.Categorie;
import sqlite.CategoriesBdd;

/**
 * Created by corentin on 17/04/14.
 */
public class CategActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categ);



        // Instance de la classe CategoriesBdd
        CategoriesBdd categoriesBdd = new CategoriesBdd(this);

        categoriesBdd.open();

        List<Categorie> categories = categoriesBdd.getCategories(); // recupere une liste des categories
        List<String> nomsCateg = categoriesBdd.getNoms();   // recupere une liste de nom des categories

        categoriesBdd.close();


        /* ============================================
            Declaration des elements du layout
        ============================================= */

        // declaration de la ViewList
        final ListView lstCateg = (ListView) findViewById(R.id.lstCateg);
        lstCateg.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nomsCateg));


        lstCateg.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                // popup suppression / modification

                final Dialog dialog = new Dialog(CategActivity.this);
                dialog.setContentView(R.layout.categ_dialog);
                dialog.setTitle("nom de la catégorie"); // a faire: get nom de la categorie

                Button btnModifier = (Button) dialog.findViewById(R.id.dialogModifier);
                btnModifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();

                        /* ===== popup modification ===== */
                        final Dialog editCategDialog = new Dialog(CategActivity.this);
                        editCategDialog.setContentView(R.layout.categ_modifdialog);
                        editCategDialog.setTitle("modification de la catégorie");
                        final EditText nomCateg = (EditText) editCategDialog.findViewById(R.id.txtNomCateg);
                        final EditText descriptionCateg = (EditText) editCategDialog.findViewById(R.id.txtDescriptionCateg);
                        nomCateg.setText("get nom categ"); // a faire
                        descriptionCateg.setText("get description categ"); // a faire


                        Button btnModifCateg = (Button) editCategDialog.findViewById(R.id.btnModif);
                        btnModifCateg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast.makeText(CategActivity.this, nomCateg.getText().toString() + " / " +descriptionCateg.getText().toString(), Toast.LENGTH_LONG).show();
                                // update


                                editCategDialog.cancel();
                                Toast.makeText(CategActivity.this, "modification terminé !", Toast.LENGTH_SHORT).show(); // si pas erreur
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
                        dialog.cancel();
                        Toast.makeText(CategActivity.this, "supprimmer", Toast.LENGTH_SHORT).show();
                        // suppression and close
                    }
                });

                dialog.show();



                // !fin popup

                return false;
            }
        });

        lstCateg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
            }
        });




        // declaration du button addCateg (sur pc fix)
        final Button btnAddCateg = (Button) findViewById(R.id.btnAddCateg);
        btnAddCateg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "ajout", Toast.LENGTH_LONG);
                toast.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

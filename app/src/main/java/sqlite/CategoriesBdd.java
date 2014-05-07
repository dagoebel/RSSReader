package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;

/**
 * Created by corentin on 17/04/14.
 */
public class CategoriesBdd {

    public final static String TAG = "ActionBarActivity";

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "rss.db";

    private static final String TABLE_CATEGORIES = "table_categories";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOM = "Nom";
    private static final int NUM_COL_NOM = 1;
    private static final String COL_DESCRIPTION = "Description";
    private static final int NUM_COL_DESCRIPTION = 2;

    private SQLiteDatabase bdd;

    private BddSqLite maBdd;

    public CategoriesBdd(Context context){
        //On créer la BDD et sa table
        maBdd = new BddSqLite(context, NOM_BDD, null, VERSION_BDD);
    }

    /**
     * ouverture de la bdd
     */
    public void open(){
        //on ouvre la BDD
        bdd = maBdd.getWritableDatabase();
    }

    /**
     * Fermeture de la bdd
     */
    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    /**
     * Recuperation de la bdd
     * @return SQLiteDatabase
     */
    public SQLiteDatabase getBDD(){
        return bdd;
    }

    /**
     * Insertion d'une categorie dans la bdd
     * @param categorie
     * @return
     */
    public long insertCategorie(Categorie categorie){
        ContentValues values = new ContentValues();
        //on ajoute une valeur associé
        values.put(COL_NOM, categorie.getNom());
        values.put(COL_DESCRIPTION, categorie.getDescription());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_CATEGORIES, null, values);
    }

    /**
     * Modification d'une categorie dans la bdd
     * @param id
     * @param categorie
     * @return
     */
    public int updateCategorie(int id, Categorie categorie){
        ContentValues values = new ContentValues();
        values.put(COL_NOM, categorie.getNom());
        values.put(COL_DESCRIPTION, categorie.getDescription());
        return bdd.update(TABLE_CATEGORIES, values, COL_ID + " = " +id, null);
    }

    /**
     * Suppression d'une categorie grâce à l'id
     * @param id
     * @return
     */
    public int removeCategorieWithID(int id){
        //Suppression d'une categorie grâce à l'ID
        return bdd.delete(TABLE_CATEGORIES, COL_ID + " = " +id, null);
    }



    /**
     * Recupere un liste des categories de la bdd
     * @return
     */
    public ArrayList<Categorie> getCategories(){

        ArrayList<Categorie> categories = new ArrayList<Categorie>();

        Cursor c = bdd.query(TABLE_CATEGORIES, new String[] {COL_ID, COL_NOM, COL_DESCRIPTION}, null, null, null, null, null);

        Categorie categorie;

        while(c.moveToNext()){
            categorie = cursorToCategorie(c);
            categories.add(categorie);
        }
        c.close();
        //si aucun élément n'a été retourné dans la requête, categories est vide

        return categories;
    }

    public Categorie getCategorieWithNom(String nom){
        Cursor c = bdd.query(TABLE_CATEGORIES, new String[] {COL_ID, COL_NOM, COL_DESCRIPTION}, COL_NOM + " LIKE \"" + nom +"\"", null, null, null, null);
        Categorie categorie;
        if(c.moveToFirst()) {
            categorie = cursorToCategorie(c);
        }
        else {
            //si aucun élément n'a été retourné dans la requête, on renvoie null
            categorie = null;
        }
        c.close();
        return categorie;
    }


    // permet de convertir un cursor en une categorie
    private Categorie cursorToCategorie(Cursor c){
        //On créé un livre
        Categorie categorie = new Categorie();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        categorie.setId(c.getInt(NUM_COL_ID));
        categorie.setNom(c.getString(NUM_COL_NOM));
        categorie.setDescription(c.getString(NUM_COL_DESCRIPTION));

        //On retourne la categorie
        return categorie;
    }



    /**
     * Recupere une liste de noms des categories
     * @return List<String>
     */
    public List<String> getNoms(){
        List<String> noms = new ArrayList<String>();

        Cursor c = bdd.query(TABLE_CATEGORIES, new String[] {COL_ID, COL_NOM, COL_DESCRIPTION}, null, null, null, null, null);

        Categorie categorie = new Categorie();

        while(c.moveToNext()){
            categorie =  cursorToCategorie(c);
            noms.add(categorie.getNom());
        }

        return noms;
    }

}

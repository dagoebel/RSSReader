package sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by corentin on 17/04/14.
 */
public class BddSqLite extends SQLiteOpenHelper {

    // table categorie
    private static final String TABLE_CATEGORIES = "table_categories";
    private static final String COL_IDCATEGORIE = "ID";
    private static final String COL_NOMCATEGORIE = "Nom";
    private static final String COL_DESCRIPTIONCATEGORIE = "Description";

    private static final String CREATE_BDD_CATEGORIES = "CREATE TABLE " + TABLE_CATEGORIES + " ("
            + COL_IDCATEGORIE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOMCATEGORIE + " TEXT NOT NULL, " + COL_DESCRIPTIONCATEGORIE + " TEXT NULL);";


    // table FluxRss
    private static final String TABLE_FLUXRSSS = "table_fluxrss";
    private static final String COL_IDFLUXRSS = "ID";
    private static final String COL_NOMFLUXRSS = "Nom";
    private static final String COL_URLFLUXRSS = "Url";
    private static final String COL_CONTENUFLUXRSS = "Contenu";

    private static final String CREATE_BDD_FLUXRSS = "CREATE TABLE " + TABLE_FLUXRSSS + " ("
            + COL_IDFLUXRSS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOMFLUXRSS + " TEXT NOT NULL, " + COL_URLFLUXRSS + " TEXT NOT NULL, " + COL_CONTENUFLUXRSS + " TEXT NOT NULL);";



    public BddSqLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on créer la table grâce a la variable CREATE_BDD
        db.execSQL(CREATE_BDD_CATEGORIES);
        db.execSQL(CREATE_BDD_FLUXRSS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Suppression de la table puis création pour réinitialiser les id à 0
        db.execSQL("DROP TABLE " + TABLE_CATEGORIES + ";");
        db.execSQL("DROP TABLE " + TABLE_FLUXRSSS + ";");
        onCreate(db);
    }
}

package sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by corentin on 17/04/14.
 */
public class BddSqLite extends SQLiteOpenHelper {
    private static final String TABLE_CATEGORIES = "table_categories";
    private static final String COL_ID = "ID";
    private static final String COL_NOM = "Nom";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_CATEGORIES + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOM + " TEXT NOT NULL);";

    public BddSqLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on créer la table grâce a la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Suppression de la table puis création pour réinitialiser les id à 0
        db.execSQL("DROP TABLE " + TABLE_CATEGORIES + ";");
        onCreate(db);
    }
}

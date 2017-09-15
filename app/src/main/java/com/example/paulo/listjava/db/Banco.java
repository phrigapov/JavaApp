package com.example.paulo.listjava.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.sql.Types.INTEGER;
import static org.xmlpull.v1.XmlPullParser.TEXT;

/**
 * Created by Paulo on 13/09/2017.
 */

public class Banco extends SQLiteOpenHelper {
    private static Banco sInstance;

    public static synchronized Banco getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Banco(context.getApplicationContext());
        }
        return sInstance;
    }

    public static final String TABLE = "comments";
    private static final String DATABASE_NAME = "ListaDB.db";
    private static final int VERSION = 1;

    public Banco(Context context) {
        super(context, "ListaDB", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE lista (id INTEGER primary key autoincrement, nome TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS lista");
        onCreate(db);
    }
}

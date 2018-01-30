package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import utils.Constants;

/**
 * class is used to initialise db
 */

public class DB_Base extends SQLiteOpenHelper {
    Context context;

    String sqlQuery="CREATE TABLE IF NOT EXISTS "+ Constants.TABLE_NAME +" ("+
            Constants.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            Constants.PRODUCT_NAME+" TEXT )";

    public DB_Base(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            context.deleteDatabase(Constants.DB_NAME);
            onCreate(sqLiteDatabase);

    }
}

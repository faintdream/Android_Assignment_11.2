package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import utils.Constants;

/**
 * here we declare functions related to opening and closing of db connection
 */

public class DBHelper {

    DB_Base db_base=null;
    Context context=null;
    static DBHelper dbHelper;
    public SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context){
        this.context=context;
        db_base= new DB_Base(context, Constants.DB_NAME,null,Constants.DB_VERSION);
    }


    public static DBHelper getInstance(Context context){

        if(dbHelper==null){
            dbHelper= new DBHelper(context);
        }
        return dbHelper;
    }

    public void OpenConnection(){
        try{
            sqLiteDatabase=db_base.getWritableDatabase();
        }catch (Exception e){
            sqLiteDatabase=db_base.getReadableDatabase();
        }
    }

    public void  CloseConnection(){
        if (sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
    }


}

package vn.magik.todo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.magik.todo.bean.AppConstant;

/**
 * Created by TOAN on 10/2/2016.
 */

public class MySQLite  extends SQLiteOpenHelper {
    private static MySQLite sqLite;
    public static SQLiteDatabase reader;
    public static SQLiteDatabase writer;

    public MySQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTable(db);
        onCreate(db);

    }
    private void createTable(SQLiteDatabase db){
        db.execSQL(AppConstant.TABLE_NOTE.NOTE_CREATE);
    }
    private void dropTable(SQLiteDatabase db){
        db.execSQL(AppConstant.TABLE_NOTE.NOTE_DROP);
    }
    public static void openSession(Context context){
        sqLite = new MySQLite(context, AppConstant.SQL_NAME, null, AppConstant.SQL_VERSION);
        reader =sqLite.getWritableDatabase();
        writer = sqLite.getReadableDatabase();
    }
    public static void closeSession(){
        reader.close();
        writer.close();
    }
    public static void beginTran(){
        writer.beginTransaction();
    }
    public static void endTran(){
        writer.setTransactionSuccessful();
        writer.endTransaction();
    }
}
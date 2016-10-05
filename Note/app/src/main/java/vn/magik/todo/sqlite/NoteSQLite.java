package vn.magik.todo.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import vn.magik.todo.bean.AppConstant;
import vn.magik.todo.bean.entity.Note;

/**
 * Created by TOAN on 10/2/2016.
 */

public class NoteSQLite {
    public static List<Note> getListNote(Context context){
        List<Note> listResult = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s", AppConstant.TABLE_NOTE.TABLE_NOTE);

        MySQLite.openSession(context);
        Cursor cursor = MySQLite.reader.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setId(cursor.getInt(0));
                note.setTitle(cursor.getString(1));
                note.setText(cursor.getString(2));
                note.setStatus(cursor.getString(3));
                note.setLevel(cursor.getString(4));
                note.setTime(cursor.getString(5));
                listResult.add(note);
            } while(cursor.moveToNext());
        }

        MySQLite.closeSession();
        return listResult;
    }
    public static void addNote(Context context, Note note){
        MySQLite.openSession(context);
        MySQLite.beginTran();
        ContentValues values = new ContentValues();
        values.put(AppConstant.TABLE_NOTE.NOTE_TITLE, note.getTitle());
        values.put(AppConstant.TABLE_NOTE.NOTE_TEXT, note.getText());
        values.put(AppConstant.TABLE_NOTE.NOTE_STATUS, note.getStatus());
        values.put(AppConstant.TABLE_NOTE.NOTE_LEVEL, note.getLevel());
        values.put(AppConstant.TABLE_NOTE.NOTE_TIME, note.getTime());

        MySQLite.writer.insert(AppConstant.TABLE_NOTE.TABLE_NOTE, null, values);
        MySQLite.endTran();
        MySQLite.closeSession();
    }

    public static void updateNote(Context context, Note note){
        String sql = String.format("UPDATE %s SET %s='%s',%s='%s',%s='%s',%s='%s',%s='%s' WHERE %s=%d",
                AppConstant.TABLE_NOTE.TABLE_NOTE,
                AppConstant.TABLE_NOTE.NOTE_TITLE, note.getTitle(),
                AppConstant.TABLE_NOTE.NOTE_TEXT, note.getText(),
                AppConstant.TABLE_NOTE.NOTE_STATUS,note.getStatus(),
                AppConstant.TABLE_NOTE.NOTE_LEVEL, note.getLevel(),
                AppConstant.TABLE_NOTE.NOTE_TIME, note.getTime(),
                AppConstant.TABLE_NOTE.NOTE_ID, note.getId());
        MySQLite.openSession(context);
        MySQLite.writer.execSQL(sql);
        MySQLite.closeSession();
    }
    public static void removeNote(Context context, int id){
        String sql = String.format("DELETE FROM %s WHERE %s=%d", AppConstant.TABLE_NOTE.TABLE_NOTE, AppConstant.TABLE_NOTE.NOTE_ID, id);
        MySQLite.openSession(context);
        MySQLite.writer.execSQL(sql);
        MySQLite.closeSession();
    }
}

package vn.magik.todo.bean;

/**
 * Created by TOAN on 10/2/2016.
 */

public class AppConstant {
    public static final String SQL_NAME = "note";
    public static final int SQL_VERSION = 1;

    public static class STATUS{
        public static final String LOW = "LOW";
        public static final String MEDIUM = "MEDIUM";
        public static final String HIGHT = "HIGHT";
    }


    public static class TABLE_NOTE{
        public static final String TABLE_NOTE = "table_title_note";
        public static final String NOTE_ID = "note_id";
        public static final String NOTE_TITLE = "note_title";
        public static final String NOTE_TEXT = "note_text";
        public static final String NOTE_STATUS = "note_status";
        public static final String NOTE_LEVEL = "note_level";
        public static final String NOTE_TIME = "note_time";
        public static final String NOTE_CREATE = String.format("CREATE TABLE IF NOT EXISTS %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT," +
                "%s TEXT," +
                "%s TEXT," +
                "%s TEXT," +
                "%s TEXT);", TABLE_NOTE, NOTE_ID, NOTE_TITLE, NOTE_TEXT, NOTE_STATUS, NOTE_LEVEL, NOTE_TIME);
        public static final String NOTE_DROP = String.format("DROP TABLE IF EXISTS %s;", TABLE_NOTE);
    }
}

package ca.wmcd.routiner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WalrusCow on 3/17/15.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "routiner_db";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_GOAL = "goal";
    private static final String KEY_WEEKDAYS = "weekdays";
    private static final String KEY_DAY_INTERVAL = "day_interval";
    private static final String KEY_TIME = "time";

    private static final String ROUTINES_TABLE_NAME = "routines";
    private static final String ROUTINES_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + ROUTINES_TABLE_NAME + "(" +
            KEY_GOAL + " TEXT, " +
            KEY_WEEKDAYS + " INTEGER, " +
            KEY_DAY_INTERVAL + " INTEGER, " +
            KEY_TIME + " INTEGER);";


    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ROUTINES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}

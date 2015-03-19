package ca.wmcd.routiner;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WalrusCow on 3/18/15. Class to house information about the routine database.
 */
public class RoutineDatabase {
    private static final String DB_NAME = "routines_db";
    private static final int DB_VERSION = 1;

    // Routines table
    private static final String ROUTINES_TABLE_NAME = "routines";
    private static final String ROUTINES_KEY_GOAL = "goal";
    private static final String ROUTINES_KEY_WEEKDAYS = "weekdays";
    private static final String ROUTINES_KEY_DAY_INTERVAL = "day_interval";
    private static final String ROUTINES_KEY_TIME = "time";

    private RoutineDatabase() {
    }

    /**
     * Save a Routine to the database. This creates a new thread.
     *
     * @param context
     *         The context to use for database operations.
     * @param routine
     *         The routine to save to the database.
     */
    public static void save(Context context, Routine routine) {
        Thread saveThread = new RoutineSaveThread(context, routine);
        saveThread.start();
    }

    private static class RoutineSaveThread extends Thread {
        private Context context;
        private Routine routine;

        /**
         * @param context
         *         The context to use for database operations.
         * @param routine
         *         The routine to save to the database.
         */
        public RoutineSaveThread(Context context, Routine routine) {
            super("SaveRoutineThread");
            this.context = context;
            this.routine = routine;
        }

        @Override
        public void run() {
            OpenHelper openHelper = new OpenHelper(context);
            SQLiteDatabase db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues(3);
            values.put(ROUTINES_KEY_GOAL, routine.goal);
            values.put(ROUTINES_KEY_TIME, routine.timeMin);
            if (routine.weekdayMask != null)
                values.put(ROUTINES_KEY_WEEKDAYS, routine.weekdayMask);
            else
                values.put(ROUTINES_KEY_DAY_INTERVAL, routine.dayInterval);

            db.insert(ROUTINES_TABLE_NAME, null, values);
        }
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ROUTINES_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        private static final String ROUTINES_TABLE_CREATE =
                "CREATE TABLE IF NOT EXISTS " + ROUTINES_TABLE_NAME + "(" +
                ROUTINES_KEY_GOAL + " TEXT, " +
                ROUTINES_KEY_WEEKDAYS + " INTEGER, " +
                ROUTINES_KEY_DAY_INTERVAL + " INTEGER, " +
                ROUTINES_KEY_TIME + " INTEGER);";
    }
}

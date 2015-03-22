package ca.wmcd.routiner.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by WalrusCow on 3/18/15. Class to house information about the routine database.
 */
public class RoutineDatabase {
    private static final String DB_NAME = "routines_db";
    private static final int DB_VERSION = 1;

    // Routines table
    private static final String ROUTINES_TABLE_NAME = "routines";
    private static final String ROUTINES_KEY_ID = "id";
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

    public static interface GetRoutinesCallback {
        public void call(List<Routine> routines);
    }

    public static void getRoutines(Context context, GetRoutinesCallback callback) {
        GetRoutinesTask getTask = new GetRoutinesTask(callback);
        getTask.execute(context);
    }

    private static class GetRoutinesTask extends AsyncTask<Context, Void, List<Routine>> {
        private final WeakReference<GetRoutinesCallback> callbackWeakReference;

        public GetRoutinesTask(GetRoutinesCallback callback) {
            super();
            callbackWeakReference = new WeakReference<>(callback);
        }

        @Override
        protected List<Routine> doInBackground(Context... contexts) {
            Context context = contexts[0];
            SQLiteDatabase db = new OpenHelper(context).getReadableDatabase();

            Cursor cursor = db.query(ROUTINES_TABLE_NAME, null, null, null, null, null, null);
            cursor.moveToFirst();
            int goalKey = cursor.getColumnIndex(ROUTINES_KEY_GOAL);
            int weekdaysKey = cursor.getColumnIndex(ROUTINES_KEY_WEEKDAYS);
            int dayIntervalKey = cursor.getColumnIndex(ROUTINES_KEY_DAY_INTERVAL);
            int timeKey = cursor.getColumnIndex(ROUTINES_KEY_TIME);
            int idKey = cursor.getColumnIndex(ROUTINES_KEY_ID);

            LinkedList<Routine> routines = new LinkedList<>();
            while (!cursor.isAfterLast()) {
                Routine routine = new Routine();
                if (weekdaysKey != -1)
                    routine.weekdayMask = cursor.getInt(weekdaysKey);
                else
                    routine.dayInterval = cursor.getInt(dayIntervalKey);
                routine.goal = cursor.getString(goalKey);
                routine.timeMin = cursor.getInt(timeKey);
                routine.id = cursor.getInt(idKey);
                routines.add(routine);
                cursor.moveToNext();
            }

            ArrayList<Routine> routineArrayList = new ArrayList<>(routines.size());
            routineArrayList.addAll(routines);

            cursor.close();
            return routineArrayList;
        }

        @Override
        protected void onPostExecute(List<Routine> routines) {
            GetRoutinesCallback callback = callbackWeakReference.get();
            if (callback != null) {
                callback.call(routines);
            }
        }
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
                ROUTINES_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ROUTINES_KEY_GOAL + " TEXT, " +
                ROUTINES_KEY_WEEKDAYS + " INTEGER, " +
                ROUTINES_KEY_DAY_INTERVAL + " INTEGER, " +
                ROUTINES_KEY_TIME + " INTEGER);";
    }
}

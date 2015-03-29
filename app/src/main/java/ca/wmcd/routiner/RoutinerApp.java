package ca.wmcd.routiner;

import android.app.Application;

import ca.wmcd.routiner.data.Routine;
import ca.wmcd.routiner.data.RoutineDatabase;
import timber.log.Timber;

public class RoutinerApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        RoutineDatabase.getNextScheduledRoutine(this, new Callback<Routine>() {
            @Override
            public void call(Routine data) {
                RoutineDatabase.scheduleRoutineNotification(RoutinerApp.this, data);
            }
        });
    }
}

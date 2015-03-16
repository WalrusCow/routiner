package ca.wmcd.routiner;

import android.app.Application;

import timber.log.Timber;

public class RoutinerApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}

package ca.wmcd.routiner;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

/**
 * Created by WalrusCow on 3/15/15.
 * Service to send notifications when a routine is due.
 */
public class RoutineNotifier extends Service {
    public RoutineNotifier() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleStart(intent);
        return START_FLAG_RETRY;
    }

    private void handleStart(Intent intent) {
        // TODO: Notify or something
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Routine Notifier");
        builder.setContentText("This is the details");
        builder.setSmallIcon(R.drawable.icon);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(1, builder.build());
    }
}

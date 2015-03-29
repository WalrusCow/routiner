package ca.wmcd.routiner.data;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.util.List;

import ca.wmcd.routiner.Callback;
import ca.wmcd.routiner.R;
import timber.log.Timber;

/**
 * Created by WalrusCow on 3/15/15.
 * Service to send notifications when a routine is due.
 */
public class RoutineNotifierService extends Service {
    public RoutineNotifierService() {
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
        final Context context = this;
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.icon);

        final NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        RoutineDatabase.getScheduledRoutines(context, new Callback<List<Routine>>() {
            @Override
            public void call(List<Routine> routines) {
                for (Routine routine : routines) {
                    Timber.d("Notifying for routine %s", routine.goal);
                    builder.setContentTitle("It's time!");
                    builder.setContentText("It's time to " + routine.goal);
                    nm.notify(routine.id, builder.build());

                    routine.scheduledTime = routine.getNextScheduledTime();
                    RoutineDatabase.save(context, routine);
                }

                RoutineDatabase.getNextScheduledRoutine(context, new Callback<Routine>() {
                    @Override
                    public void call(Routine routine) {
                        RoutineDatabase.scheduleRoutineNotification(context, routine);
                    }
                });
            }
        });
    }
}

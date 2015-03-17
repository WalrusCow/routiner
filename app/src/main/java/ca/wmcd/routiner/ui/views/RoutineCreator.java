package ca.wmcd.routiner.ui.views;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectViews;
import butterknife.OnClick;
import ca.wmcd.routiner.R;
import ca.wmcd.routiner.RoutineNotifier;

/**
 * Created by WalrusCow on 3/15/15.
 * View to create a new routine
 */
public class RoutineCreator extends LinearLayout {
    @InjectViews(
            {R.id.weekday_sunday, R.id.weekday_monday, R.id.weekday_tuesday, R.id.weekday_wednesday,
             R.id.weekday_thursday, R.id.weekday_friday, R.id.weekday_saturday})
    List<View> weekdayViews;

    public RoutineCreator(Context context) {
        super(context);
        init(context);
    }

    public RoutineCreator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RoutineCreator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public RoutineCreator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_routine_creator, this);
        ButterKnife.inject(this, this);
    }

    @OnClick(R.id.every_n_days)
    public void everyDaysClick() {
        for (View v : weekdayViews) {
            v.setEnabled(!v.isEnabled());
        }
    }

    @OnClick(R.id.clicky)
    public void clickyClick() {
        Context context = getContext();
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent intent = PendingIntent.getService(
                context, 0, new Intent(context, RoutineNotifier.class), 0);

        // Five seconds from now
        long wakeTime = Calendar.getInstance().getTimeInMillis() + 5000;
        am.set(AlarmManager.RTC_WAKEUP, wakeTime, intent);
    }
}

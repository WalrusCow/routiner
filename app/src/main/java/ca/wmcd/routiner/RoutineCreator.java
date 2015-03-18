package ca.wmcd.routiner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

/**
 * Created by WalrusCow on 3/15/15.
 * View to create a new routine
 */
public class RoutineCreator extends LinearLayout {
    @InjectViews(
            {R.id.weekday_sunday, R.id.weekday_monday, R.id.weekday_tuesday, R.id.weekday_wednesday,
             R.id.weekday_thursday, R.id.weekday_friday, R.id.weekday_saturday})
    List<WeekdaySelector> weekdayViews;

    @InjectView(R.id.routine_goal_input) EditText goalInput;

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

    @OnClick(R.id.create_routine)
    public void createRoutine() {

        String goalString = goalInput.getText().toString();
        int b = 0;
        for (WeekdaySelector weekdayView : weekdayViews) {
            if (weekdayView.isSelected()) {
                b |= 1 << weekdayView.weekday;
            }
        }

        Context context = getContext();
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent intent = PendingIntent.getService(
                context, 1, new Intent(context, RoutineNotifier.class), PendingIntent.FLAG_CANCEL_CURRENT);

        // Five seconds from now
        long wakeTime = Calendar.getInstance().getTimeInMillis() + 5000;
        am.set(AlarmManager.RTC_WAKEUP, wakeTime, intent);
    }
}

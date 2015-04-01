package ca.wmcd.routiner.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.NavUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import ca.wmcd.routiner.R;
import ca.wmcd.routiner.data.Routine;
import ca.wmcd.routiner.data.RoutineDatabase;

/**
 * Created by WalrusCow on 3/15/15.
 * View to create a new routine
 */
public class RoutineCreatorView extends RelativeLayout {
    @InjectViews(
            {R.id.weekday_sunday, R.id.weekday_monday, R.id.weekday_tuesday, R.id.weekday_wednesday,
             R.id.weekday_thursday, R.id.weekday_friday, R.id.weekday_saturday})
    List<WeekdaySelectorView> weekdayViews;

    @InjectView(R.id.routine_goal_input) EditText goalInput;
    @InjectView(R.id.time_selector) TimeSelectorView timeSelector;
    private Routine editRoutine;

    public RoutineCreatorView(Context context) {
        super(context);
        init(context);
    }

    public RoutineCreatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RoutineCreatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public RoutineCreatorView(Context context, AttributeSet attrs, int defStyleAttr,
                              int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_routine_creator, this);
        ButterKnife.inject(this);
    }

    public void editRoutine(Routine routine) {
        // Set a routine that we are editing
        editRoutine = routine;
        for (WeekdaySelectorView weekdayView : weekdayViews) {
            weekdayView.setSelected(routine.weekdaySelected(weekdayView.weekday));
        }
        goalInput.setText(routine.goal);
    }

    @OnClick(R.id.create_routine)
    public void createRoutine() {
        Routine routine = new Routine();
        routine.weekdayMask = 0;
        for (WeekdaySelectorView weekdayView : weekdayViews) {
            if (weekdayView.isSelected()) {
                routine.setWeekdaySelected(weekdayView.weekday, true);
            }
        }

        if (routine.weekdayMask == 0) {
            // No days set
            // TODO: Notify user
            return;
        }

        routine.timeMin = timeSelector.getHour() * 60 + timeSelector.getMinute();
        routine.goal = goalInput.getText().toString().trim();
        if (routine.goal.equals("")) {
            // No goal set
            // TODO: Notify user
            goalInput.setError("Required");
            return;
        }

        if (editRoutine != null) {
            routine.id = editRoutine.id;
        }

        routine.scheduledTime = routine.getNextScheduledTime();
        Context context = getContext();
        RoutineDatabase.save(context, routine);

        // Done, so close this activity
        NavUtils.navigateUpFromSameTask((Activity) context);
    }
}

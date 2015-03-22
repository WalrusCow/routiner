package ca.wmcd.routiner.ui;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import ca.wmcd.routiner.R;
import ca.wmcd.routiner.data.Routine;

/**
 * Created by WalrusCow on 3/19/15. Display a routine as in a list
 */
public class RoutineView extends RelativeLayout {
    @InjectView(R.id.routine_title_text) TextView routineTitleView;

    @InjectViews(
            {R.id.weekday_sunday, R.id.weekday_monday, R.id.weekday_tuesday, R.id.weekday_wednesday,
             R.id.weekday_thursday, R.id.weekday_friday, R.id.weekday_saturday})
    List<WeekdayCircleView> weekdayViews;
    private Routine routine;

    public RoutineView(Context context) {
        super(context);
        init();
    }

    public RoutineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoutineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RoutineView(Context context, AttributeSet attrs, int defStyleAttr,
                       int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private String formatTimeString(int timeMin) {
        // Special times
        if (timeMin == 0) return "midnight";
        if (timeMin == 60 * 12) return "noon";

        int hours = timeMin / 60;
        boolean am = hours < 12;
        int mins = timeMin % 60;

        if (hours > 12) {
            hours -= 12;
        }

        return Integer.toString(hours) + ":" + String.format("%02d", mins) + (am ? " am" : " pm");
    }

    public void setRoutine(Routine routine) {
        // Capitalize the first character in the goal
        String goal = routine.goal.substring(0, 1).toUpperCase();
        goal += routine.goal.substring(1);

        String timeString = formatTimeString(routine.timeMin);
        String titleString = goal + " at " + timeString;
        routineTitleView.setText(titleString);
        for (int i = 0; i < 7; ++i) {
            if (routine.weekdaySelected(i)) {
                weekdayViews.get(i).setSelected(true);
            }
            else {
                weekdayViews.get(i).setSelected(false);
            }
        }

        this.routine = routine;
    }

    @OnClick
    public void onClick() {
        // Edit the routine we clicked
        Intent intent = new Intent(getContext(), RoutineActivity.class);
        intent.putExtra(RoutineActivity.EXTRA_ROUTINE, routine);
        getContext().startActivity(intent);
    }

    private void init() {
        inflate(getContext(), R.layout.view_routine, this);
        ButterKnife.inject(this);
        setBackgroundResource(R.drawable.round_border_background);
    }
}

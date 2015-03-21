package ca.wmcd.routiner.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ca.wmcd.routiner.R;
import ca.wmcd.routiner.data.Routine;

/**
 * Created by WalrusCow on 3/19/15.
 * Display a routine as in a list
 */
public class RoutineView extends RelativeLayout {
    @InjectView(R.id.routine_title_text) TextView routineTitleView;

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

        return Integer.toString(hours) + ":" + String.format("%02d", mins) + " " + (am ? "am" : "pm");
    }

    public void setRoutine(Routine routine) {
        // Capitalize the first character in the goal
        String goal = routine.goal.substring(0, 1).toUpperCase();
        goal += routine.goal.substring(1);

        String timeString = formatTimeString(routine.timeMin);
        String titleString = goal + " at " + timeString;
        routineTitleView.setText(titleString);
    }

    private void init() {
        inflate(getContext(), R.layout.view_routine, this);
        ButterKnife.inject(this);
    }
}

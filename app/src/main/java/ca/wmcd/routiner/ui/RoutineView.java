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
    @InjectView(R.id.routine_goal) TextView routineGoalView;

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

    public void setRoutine(Routine routine) {
        // Update this view to show the specified routine
        routineGoalView.setText(routine.goal);
    }

    private void init() {
        inflate(getContext(), R.layout.view_routine, this);
        ButterKnife.inject(this, this);
    }
}

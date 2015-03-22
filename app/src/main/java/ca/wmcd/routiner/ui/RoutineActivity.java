package ca.wmcd.routiner.ui;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ca.wmcd.routiner.R;
import ca.wmcd.routiner.data.Routine;

/**
 * Created by WalrusCow on 3/15/15.
 */
public class RoutineActivity extends Activity {
    @InjectView(R.id.routine_creator_view) RoutineCreatorView creatorView;
    public static final String EXTRA_ROUTINE = "ca.wmcd.routiner.ui.RoutineActivity.EXTRA_ROUTINE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        ButterKnife.inject(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // We were started to edit a routine
            Routine routine = extras.getParcelable(EXTRA_ROUTINE);
            creatorView.editRoutine(routine);
        }
    }
}

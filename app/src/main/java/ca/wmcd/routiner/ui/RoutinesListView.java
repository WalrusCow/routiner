package ca.wmcd.routiner.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.List;

import ca.wmcd.routiner.Callback;
import ca.wmcd.routiner.R;
import ca.wmcd.routiner.data.Routine;
import ca.wmcd.routiner.data.RoutineDatabase;

/**
 * Created by WalrusCow on 3/19/15.
 * List all routines.
 */
public class RoutinesListView extends RecyclerView {
    public RoutinesListView(Context context) {
        super(context);
        init();
    }

    public RoutinesListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoutinesListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RoutinesListView(Context context, AttributeSet attrs, int defStyleAttr,
                            int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public RoutinesListAdapter getAdapter() {
        return (RoutinesListAdapter) super.getAdapter();
    }

    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(new RoutinesListAdapter());

        int verticalSpace =
                getResources().getDimensionPixelSize(R.dimen.routine_view_vertical_space);
        addItemDecoration(new SpaceItemDecoration(verticalSpace));

        RoutineDatabase.getRoutines(getContext(), new Callback<List<Routine>>() {
            @Override
            public void call(List<Routine> routines) {
                RoutinesListAdapter adapter = getAdapter();
                adapter.routines = routines;
                adapter.notifyDataSetChanged();
            }
        });
    }
}

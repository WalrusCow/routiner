package ca.wmcd.routiner;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by WalrusCow on 3/19/15.
 * Adapter for the routines list view.
 */
public class RoutinesListAdapter extends RecyclerView.Adapter<RoutinesListAdapter.ViewHolder> {
    public List<Routine> routines = Collections.emptyList();

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.routineView.setRoutine(routines.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new RoutineView(parent.getContext()));
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RoutineView routineView;
        public ViewHolder(View v) {
            super(v);
            routineView = (RoutineView) v;
        }
    }
}


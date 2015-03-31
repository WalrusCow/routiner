package ca.wmcd.routiner.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import ca.wmcd.routiner.R;
import ca.wmcd.routiner.data.Routine;

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
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view_routine_list_member, parent, false);
        return new ViewHolder(v);
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


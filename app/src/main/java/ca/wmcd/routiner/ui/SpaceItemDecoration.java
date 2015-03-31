package ca.wmcd.routiner.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Add space between items for a RecyclerView
 * Created by WalrusCow on 3/30/15.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int verticalSpace;
    private int horizontalSpace;

    public SpaceItemDecoration(int verticalSpace) {
        this(verticalSpace, 0);
    }

    public SpaceItemDecoration(int verticalSpace, int horizontalSpace) {
        this.verticalSpace = verticalSpace;
        this.horizontalSpace = horizontalSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left += horizontalSpace;
        outRect.right += horizontalSpace;
        outRect.top += verticalSpace;
        outRect.bottom += verticalSpace;
    }
}

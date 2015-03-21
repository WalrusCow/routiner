package ca.wmcd.routiner.ui;

import android.content.Context;
import android.util.AttributeSet;

import butterknife.OnClick;

/**
 * Created by WalrusCow on 3/15/15.
 * Clickable version of WeekdayCircleView.
 */
public class WeekdaySelectorView extends WeekdayCircleView {
    public WeekdaySelectorView(Context context) {
        super(context);
    }

    public WeekdaySelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeekdaySelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WeekdaySelectorView(Context context, AttributeSet attrs, int defStyleAttr,
                               int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @OnClick
    public void onClick() {
        setSelected(!isSelected());
    }
}

package ca.wmcd.routiner.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WalrusCow on 3/15/15.
 */
public class WeekdaySelector extends Button {
    public WeekdaySelector(Context context) {
        super(context);
        init();
    }

    public WeekdaySelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WeekdaySelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WeekdaySelector(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ButterKnife.inject(this, this);
    }

    @OnClick
    public void onClick() {
        setSelected(!isSelected());
    }
}

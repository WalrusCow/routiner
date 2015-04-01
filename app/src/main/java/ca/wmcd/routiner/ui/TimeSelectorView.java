package ca.wmcd.routiner.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ca.wmcd.routiner.R;

/**
 * Select a time.
 * Created by WalrusCow on 3/31/15.
 */
public class TimeSelectorView extends RelativeLayout {
    @InjectView(R.id.hour_selector) TextView hourSelector;
    @InjectView(R.id.minute_selector) TextView minuteSelector;
    @InjectView(R.id.am_selector) TextView amSelector;
    @InjectView(R.id.pm_selector) TextView pmSelector;

    private int hour = 8;
    private int minute = 0;

    public TimeSelectorView(Context context) {
        super(context);
        init(context);
    }

    public TimeSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimeSelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TimeSelectorView(Context context, AttributeSet attrs, int defStyleAttr,
                            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_time_selector, this);
        ButterKnife.inject(this);

        // Make time views always be the correct size
        int textWidth = (int) hourSelector.getPaint().measureText("12");
        hourSelector.setWidth(
                textWidth + hourSelector.getPaddingLeft() + hourSelector.getPaddingRight());

        textWidth = (int) minuteSelector.getPaint().measureText("45");
        minuteSelector.setWidth(
                textWidth + minuteSelector.getPaddingLeft() + minuteSelector.getPaddingRight());

        hourSelector.setText(Integer.toString(hour));
        minuteSelector.setText(String.format("%02d", minute));
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}

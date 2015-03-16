package ca.wmcd.routiner.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ca.wmcd.routiner.R;

/**
 * Created by WalrusCow on 3/15/15.
 */
public class WeekdaySelector extends TextView {
    private static String shortText[] = {"S", "M", "T", "W", "T", "F", "S"};
    public WeekdaySelector(Context context) {
        super(context);
        init(context, null);
    }

    public WeekdaySelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WeekdaySelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public WeekdaySelector(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        ButterKnife.inject(this, this);
        // Extract custom attribute
        if (attrs == null) {
            return;
        }

        TypedArray typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.WeekdaySelector, 0, 0);
        int weekDay = typedArray.getInt(R.styleable.WeekdaySelector_weekday, 0);
        setText(shortText[weekDay]);
        typedArray.recycle();
    }

    @OnClick
    public void onClick() {
        setSelected(!isSelected());
    }
}

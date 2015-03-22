package ca.wmcd.routiner.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import butterknife.ButterKnife;
import ca.wmcd.routiner.R;

/**
 * Created by WalrusCow on 3/20/15.
 * View a weekday in a nice little circle.
 */
public class WeekdayCircleView extends TextView {
    private static String shortText[] = {"S", "M", "T", "W", "T", "F", "S"};
    public int weekday;

    public WeekdayCircleView(Context context) {
        super(context);
        init(context, null);
    }

    public WeekdayCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WeekdayCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public WeekdayCircleView(Context context, AttributeSet attrs, int defStyleAttr,
                               int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        ButterKnife.inject(this);
        // Extract custom attribute
        if (attrs == null) {
            return;
        }

        TypedArray typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.WeekdayCircleView, 0, 0);
        weekday = typedArray.getInt(R.styleable.WeekdayCircleView_weekday, 0);
        setText(shortText[weekday]);

        boolean selected = typedArray.getBoolean(R.styleable.WeekdayCircleView_selected, false);
        typedArray.recycle();

        // Default doesn't work in XML
        if (selected) setSelected(true);
    }
}


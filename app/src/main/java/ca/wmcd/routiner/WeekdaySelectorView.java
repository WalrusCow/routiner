package ca.wmcd.routiner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WalrusCow on 3/15/15.
 */
public class WeekdaySelectorView extends TextView {
    private static String shortText[] = {"S", "M", "T", "W", "T", "F", "S"};
    public int weekday;

    public WeekdaySelectorView(Context context) {
        super(context);
        init(context, null);
    }

    public WeekdaySelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WeekdaySelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public WeekdaySelectorView(Context context, AttributeSet attrs, int defStyleAttr,
                               int defStyleRes) {
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
                context.obtainStyledAttributes(attrs, R.styleable.WeekdaySelectorView, 0, 0);
        weekday = typedArray.getInt(R.styleable.WeekdaySelectorView_weekday, 0);
        setText(shortText[weekday]);
        typedArray.recycle();

        // Default doesn't work in XML
        setSelected(true);
    }

    @OnClick
    public void onClick() {
        setSelected(!isSelected());
    }
}

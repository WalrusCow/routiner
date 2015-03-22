package ca.wmcd.routiner.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WalrusCow on 3/18/15. A Routine created by the user.
 */
public class Routine implements Parcelable {
    // Special values for `time` field
    public static final int TIME_WAKE_UP = -1;
    public static final int TIME_BEFORE_BED = -2;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Routine createFromParcel(Parcel in) {
            return new Routine(in);
        }

        public Routine[] newArray(int size) {
            return new Routine[size];
        }
    };

    private static final byte PARCEL_WEEKDAY_FLAG = 0x0;
    private static final byte PARCEL_DAY_INTERVAL_FLAG = 0x1;

    // Defined goal for the routine. What is to be accomplished?
    public String goal;
    // Mask of which weekdays are enabled for this routine.
    // if the ith bit is set then the ith weekday is enabled (0-index, 0==Sunday)
    // E.g. weekdayMask & 1 is for Sunday, weekdayMask & (1 << 1) is Monday.
    public Integer weekdayMask;
    // An interval for how often the routine is due.
    // Exactly one of weekdayMask and dayInterval must be set.
    public Integer dayInterval;
    // Time in minutes from midnight the routine is due
    // Could also be one of a few special values (see TIME_*)
    public int timeMin;

    public Integer id;

    public Routine() {
    }

    public Routine(Parcel in) {
        goal = (String) in.readValue(String.class.getClassLoader());
        boolean weekday = in.readByte() == PARCEL_WEEKDAY_FLAG;
        int when = (Integer) in.readValue(Integer.class.getClassLoader());
        if (weekday) weekdayMask = when;
        else dayInterval = when;
        timeMin = in.readInt();
        id = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public boolean weekdaySelected(int i) {
        return (weekdayMask != null) && ((weekdayMask & (1 << i)) != 0);
    }

    public void setWeekdaySelected(int i, boolean selected) {
        if (selected) {
            weekdayMask |= 1 << i;
        }
        else {
            weekdayMask &= ~(1 << i);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(goal);
        dest.writeByte(weekdayMask == null ? PARCEL_DAY_INTERVAL_FLAG : PARCEL_WEEKDAY_FLAG);
        dest.writeValue(weekdayMask == null ? dayInterval : weekdayMask);
        dest.writeInt(timeMin);
        dest.writeValue(id);
    }
}

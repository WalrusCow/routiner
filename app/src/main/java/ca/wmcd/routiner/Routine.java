package ca.wmcd.routiner;

/**
 * Created by WalrusCow on 3/18/15.
 * A Routine created by the user.
 */
public class Routine {
    // Special values for `time` field
    public static final int TIME_WAKE_UP = -1;
    public static final int TIME_BEFORE_BED = -2;

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
}

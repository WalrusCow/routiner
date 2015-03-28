package ca.wmcd.routiner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import timber.log.Timber;

/**
 * Created by WalrusCow on 3/27/15.
 * Random utilities
 */
public class Util {
    private Util() {}

    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static void logTime(long timestamp) {
        logTime("", timestamp, "");
    }

    public static void logTime(String messagePrefix, long timestamp) {
        logTime(messagePrefix, timestamp, "");
    }

    public static void logTime(long timestamp, String messageSuffix) {
        logTime("", timestamp, messageSuffix);
    }

    public static void logTime(String messagePrefix, long timestamp, String messageSuffix) {
        String message = messagePrefix + "%s %s" + messageSuffix;

        calendar.setTimeInMillis(timestamp);
        String timeString = timeFormat.format(calendar.getTime());
        String timezoneString = calendar.getTimeZone().getDisplayName();
        Timber.d(message, timeString, timezoneString);
    }
}

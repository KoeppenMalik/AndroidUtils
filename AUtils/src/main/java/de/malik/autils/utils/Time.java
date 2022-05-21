package de.malik.autils.utils;

import androidx.annotation.Nullable;

public class Time {

    /**
     * the separator that will be used for separating different time components
     */
    public static final String TIME_SEPARATOR = ":";

    /**
     * the time in milliseconds
     */
    private long mMillis;

    /**
     * creates a new instance of Time class with the time set to the given millis
     * @param millis time time in milliseconds
     */
    public Time(long millis) {
        mMillis = millis;
    }

    /**
     * creates a new instance of Time class with the time set to the given arguments
     * @param hours the hours
     * @param minutes the minutes
     * @param seconds the seconds
     * @param millis the millis
     */
    public Time(long hours, long minutes, long seconds, long millis) {
        mMillis = toMillis(hours, minutes, seconds) + millis;
    }

    /**
     * checks if the time millis of the two times are equal
     * @param obj the time object that will be compared
     * @return true if the times are equal, false otherwise
     * @throws IllegalArgumentException if the given object is not an instance of Time class
     */
    @Override
    public boolean equals(@Nullable Object obj) throws IllegalArgumentException {
        if (!(obj instanceof Time)) {
            throw new IllegalArgumentException("Argument must be an instance of Time class.");
        }
        Time time = (Time) obj;
        return mMillis == time.getMillis();
    }

    /**
     * creates a formatted string of the time, separated by ':'
     * @return a formatted string representation of the time
     */
    @Override
    public String toString() {
        long[] components = toTimeComponents();
        return components[0] + TIME_SEPARATOR +
                components[1] + TIME_SEPARATOR +
                components[2] + TIME_SEPARATOR +
                components[3];
    }

    /**
     * converts the time millis into an array of long containing in order: hours, minutes, seconds and millis
     * @return an array of long containing the time parts in order: hours, minutes, seconds and millis
     */
    public long[] toTimeComponents() {
        long hours = mMillis / 3600000;
        long minutes = (mMillis % 3600000) / 60000;
        long seconds = ((mMillis % 3600000) % 60000) / 1000;
        long millis = mMillis - toMillis(hours, minutes, seconds);
        return new long[] {hours, minutes, seconds, millis};
    }

    /**
     * converts the given arguments into milliseconds
     * @param hours the hours
     * @param minutes the minutes
     * @param seconds the seconds
     * @return converts hours, minutes, seconds into milliseconds
     */
    public long toMillis(long hours, long minutes, long seconds) {
        return hours * 3600000 + minutes * 60000 + seconds * 1000;
    }

    /**
     * calculates the difference in time between the two times
     * @param time the Time class instance which will be calculated the difference to
     * @return the time difference in milliseconds
     */
    public long getDifferenceTo(Time time) {
        return mMillis - time.getMillis();
    }

    /**
     * parses the time string to a new object of Time class
     * @param timeString the time string that will be converted
     * @return a new instance of Time class containing the data of the time string. returns null if rather the time string
     *         is not separated by ':' or the time string does not contain all time components (in order: hours:minutes:seconds:milliseconds)
     */
    public Time parseTime(String timeString) {
        String[] timeStringComponents;
        long[] timeComponents = new long[4];
        if (!timeString.contains(TIME_SEPARATOR)) {
            return null;
        }
        timeStringComponents = timeString.split(TIME_SEPARATOR);
        if (timeStringComponents.length != 4) {
            return null;
        }
        for (int i = 0; i < timeStringComponents.length; i++) {
            timeComponents[i] = Long.parseLong(timeStringComponents[i]);
        }
        return new Time(timeComponents[0], timeComponents[1], timeComponents[2], timeComponents[3]);
    }

    /**
     *
     * @return the time in milliseconds
     */
    public long getMillis() {
        return mMillis;
    }

    /**
     * sets the current milliseconds of the time
     * @param millis new milliseconds
     */
    public void setMillis(long millis) {
        this.mMillis = millis;
    }
}

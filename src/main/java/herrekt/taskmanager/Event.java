package herrekt.taskmanager;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event<T> extends Task {
    private final T date;
    private static final int TWELVE_HOUR_CLOCK_CONVERTER = 12;

    /**
     * Initialise an Event with a description and date.
     *
     * @param description Description of the event.
     * @param date Further details of when it occurs.
     */
    public Event(String description, T date) {
        super(description);
        this.date = date;
    }

    protected String getDate() {
        if (date instanceof String) {
            return (String) date;
        } else if (date instanceof LocalDateTime) {
            return convertLocalDateTimeToString();
        } else {
            String month = ((LocalDate) date).getMonth().toString().substring(0,3);
            int day = ((LocalDate) date).getDayOfMonth();
            int year = ((LocalDate) date).getYear();
            return month + SPACER + day + SPACER + year;
        }
    }

    private String convertLocalDateTimeToString() {
        String month = ((LocalDateTime) date).getMonth().toString().substring(0,3);
        int day = ((LocalDateTime) date).getDayOfMonth();
        int year = ((LocalDateTime) date).getYear();
        int hour = ((LocalDateTime) date).getHour();
        String amOrPm = "am";
        if (hour > TWELVE_HOUR_CLOCK_CONVERTER) {
            hour -= TWELVE_HOUR_CLOCK_CONVERTER;
            amOrPm = "pm";
        } else if (hour == 0) {
            hour = TWELVE_HOUR_CLOCK_CONVERTER;
        }
        int minute = ((LocalDateTime) date).getMinute();
        if (minute < 10) {
            return month + SPACER + day + SPACER + year + SPACER + hour + ":" + "0" + minute + amOrPm;
        } else {
            return month + SPACER + day + SPACER + year + SPACER + hour + ":" +  minute + amOrPm;
        }
    }

    private String dateAsSave() {
        if (this.date instanceof LocalDateTime) {
            LocalDateTime toChangeToString = (LocalDateTime) this.date;
            String hourAsString = timeInIntToString(toChangeToString.getHour());
            String minAsString = timeInIntToString(toChangeToString.getMinute());
            String toReturn = toChangeToString.toString().substring(0,10)
                    + SPACER
                    + hourAsString
                    + minAsString;
            return toReturn;
        } else {
            return this.date.toString();
        }
    }

    private String timeInIntToString(int time) {
        String timeAsString;
        if (time < 10) {
            timeAsString = "0" + time;
        } else {
            timeAsString = String.valueOf(time);
        }
        return timeAsString;
    }

    /**
     * Returns the save format of the current Event.
     * Converts the event into a string format recognizable in the save file.
     *
     * @return The event as a string.
     */
    @Override
    protected String toSave() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "E" + SAVE_FILE_SPACER
                + done + SAVE_FILE_SPACER
                + this.description + SAVE_FILE_SPACER
                + this.dateAsSave();
    }

    protected String getDescription() {
        return super.description + " (at: " + this.getDate() + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDate() + ")";
    }
}

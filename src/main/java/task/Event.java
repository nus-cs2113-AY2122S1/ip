package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    public static final String AT = "at: ";
    protected LocalDateTime fromDateAndTime;
    protected LocalDateTime toDateAndTime;
    private static final String IDENTIFIER = "E";

    public Event(String description, LocalDateTime fromDateAndTime, LocalDateTime toDateAndTime) {
        this.description = description;
        this.fromDateAndTime = fromDateAndTime;
        this.toDateAndTime = toDateAndTime;
    }

    public LocalDateTime getFromDateAndTime () {
        return fromDateAndTime;
    }

    public String getStatusIconAndDescription() {
        String icon = (isDone ? "X" : " ");
        return addSquareBrackets(IDENTIFIER) + addSquareBrackets(icon) + " " + description + " " +
                addBrackets(AT + formatDateTime(fromDateAndTime) + " to " + formatDateTime(toDateAndTime));
    }

    private String formatDateTime(LocalDateTime DateAndTime) {
        return DateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    public String getStatusIconAndDescriptionForFile() {
        String icon = (isDone ? "1" : "0");
        return  IDENTIFIER + SEPARATOR + icon + SEPARATOR + description + SEPARATOR + fromDateAndTime +
                SEPARATOR + toDateAndTime;
    }
}

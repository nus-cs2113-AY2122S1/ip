package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task class which contains the description of the task and date in which
 * the task needs to be done.
 */
public class Deadline extends Task{
    public static final String BY = "by: ";
    protected LocalDateTime dateAndTime;
    public static final String IDENTIFIER = "D";

    public Deadline(String description,LocalDateTime date) {
        this.description = description;
        this.dateAndTime = date;
    }

    public LocalDateTime getDateAndTime () {
        return dateAndTime;
    }

    @Override
    public String getStatusIconAndDescription() {
        String icon = (isDone ? "X" : " ");
        return addSquareBrackets(IDENTIFIER) + addSquareBrackets(icon) + " " + description + " "
                + addBrackets(BY + formatDateTime(dateAndTime));
    }

    private String formatDateTime(LocalDateTime DateAndTime) {
        return DateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String getStatusIconAndDescriptionForFile() {
        String icon = (isDone ? "1" : "0");
        return  IDENTIFIER + SEPARATOR + icon + SEPARATOR + description + SEPARATOR + dateAndTime;
    }
}

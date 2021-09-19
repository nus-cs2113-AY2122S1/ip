package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * The Deadline class manages a task that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    /* Indexes used in extracting date and time */
    private static final int DATE_INDEX = 0;
    private static final int TIME_INDEX = 1;
    /* Default time used when time cannot be extracted or found */
    private static final String DEFAULT_TIME = "0000";

    /* Date of deadline */
    protected LocalDate date;
    /* Timestamp of deadline */
    protected String time;

    /**
     * Initialises a new incomplete task with a deadline.
     *
     * @param description Description of task to be done.
     * @param dateTime    When the task need to be done by.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        date = extractDate(dateTime);
        time = extractTime(dateTime);
    }

    /**
     * Returns the description along with the date time of the deadline.
     *
     * @return Full description of task.
     */
    @Override
    public String getFullDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String dateTime = String.format("%s %s", date.format(formatter), time);
        return String.format("%s (by: %s)", super.getDescription(), dateTime);
    }

    /**
     * Returns the date and time as a string.
     *
     * @return String containing the date and time seperated by a space.
     */
    public String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        return String.format("%s %s", date.format(formatter), time);
    }

    /**
     * Returns the Deadline icon.
     *
     * @return Deadline icon.
     */
    @Override
    public String getTaskIcon() {
        return "D";
    }

    /**
     * Extracts the date from the given string.
     *
     * @param dateTime String to extract from.
     * @return LocalDate representation of the date.
     */
    private LocalDate extractDate(String dateTime) {
        String[] tokens = dateTime.split(" ");
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                .toFormatter();
        return LocalDate.parse(tokens[DATE_INDEX], formatter);
    }

    /**
     * Extracts the time from the given string.
     *
     * @param dateTime String to extract from.
     * @return Timestamp of the extracted time.
     */
    private String extractTime(String dateTime) {
        String[] tokens = dateTime.split(" ");
        if (tokens.length <= 1) {
            return DEFAULT_TIME;
        }
        return tokens[TIME_INDEX];
    }
}

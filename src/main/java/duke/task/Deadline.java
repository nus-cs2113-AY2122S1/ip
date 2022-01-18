package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadline extends Task {

    private static final String DESCRIPTION_EMPTY_ERROR_MESSAGE = "The description of a"
            + " deadline cannot be empty.";
    private static final String BY_EMPTY_ERROR_MESSAGE = "The time of a deadline should "
            + "be completed cannot be "
            + "empty.";

    private static final String DATE_TIME_FORMAT = "MMM dd yyyy";

    protected LocalDate by;
    protected DateTimeFormatter printFormatter;

    /**
     * Creates a Deadline Task
     *
     * @param description to describe the deadline
     * @param by          the time at which the deadline should be completed
     * @throws IllegalArgumentException if the description or by is empty or null
     * @throws DateTimeParseException   unable to parse datetime according to the format
     */
    public Deadline(String description, String by)
            throws IllegalArgumentException, DateTimeParseException {
        super(description);

        printFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        if (isStringNullOrEmpty(description)) {
            throw new IllegalArgumentException(DESCRIPTION_EMPTY_ERROR_MESSAGE);
        } else if (isStringNullOrEmpty(by)) {
            throw new IllegalArgumentException(BY_EMPTY_ERROR_MESSAGE);
        }

        this.by = LocalDate.parse(by);
    }

    /**
     * Formats deadline object to a savable string format
     *
     * @return formatted Deadline object as a String to be saved
     */
    @Override
    public String saveToText() {
        return "D | " + super.saveToText() + " | " + by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(printFormatter));
    }
}

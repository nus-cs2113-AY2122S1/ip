package Duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;
    private String unformattedBy;
    private String formattedBy;

    /**
     * Class constructor for Deadline
     *
     * @param description Description of the task
     * @param unformattedBy Date and time deadline should be done by
     * @throws DateTimeParseException If date and time given by user do not follow the format
     */
    public Deadline (String description, String unformattedBy) throws DateTimeParseException {
        super(description);
        this.unformattedBy = unformattedBy;
        this.by = convertBy();
        this.formattedBy = formatBy();
    }

    /**
     * Get String by that user input, used to save to file
     *
     * @return Date and time that user input, unformatted
     */
    public String getUnformattedBy() {
        return unformattedBy;
    }

    /**
     * Get by that has been formatted to be of type LocalDateTime
     *
     * @return Date and time that has been formatted
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Converts String by to be LocalDateTime object
     *
     * @return by as a LocalDateTime object
     * @throws DateTimeParseException
     */
    private LocalDateTime convertBy() throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        return LocalDateTime.parse(unformattedBy, formatter);
    }

    /**
     * Converts LocalDateTime object to be formatted String by
     *
     * @return formatted String by
     */
    public String formatBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));
    }

    /**
     * Used to print deadline including its task type and the due date
     *
     * @return String to print when trying to print object Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }

}

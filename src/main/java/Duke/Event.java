package Duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private LocalDateTime at;
    private String unformattedAt;
    private String formattedAt;

    /**
     * Class constructor for Event
     *
     * @param description Description of the task
     * @param unformattedAt Date and time event should be on
     * @throws DateTimeParseException If date and time given by user do not follow the format
     */
    public Event (String description, String unformattedAt) throws DateTimeParseException {
        super(description);
        this.unformattedAt = unformattedAt;
        this.at = convertAt();
        this.formattedAt = formatAt();
    }

    /**
     * Get String at that user input, used to save to file
     *
     * @return Date and time that user input, unformatted
     */
    public String getUnformattedAt() {
        return unformattedAt;
    }

    /**
     * Converts String at to be LocalDateTime object
     *
     * @return at as a LocalDateTime object
     * @throws DateTimeParseException
     */
    private LocalDateTime convertAt() throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        return LocalDateTime.parse(unformattedAt, formatter);
    }

    /**
     * Converts LocalDateTime object to be formatted String at
     *
     * @return formatted String at
     */
    public String formatAt() {
        return at.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));
    }

    /**
     * Used to print event including its task type and when it occurs
     *
     * @return String to print when trying to print object Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedAt + ")";
    }

}

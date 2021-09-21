import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    /**
     * Initialises the deadline.
     *
     * @param description description input by user.
     * @param at          date of event
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Modifies the output format.
     *
     * @return the desired output format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Modifies the string format for adding to file.
     *
     * @return the correct format for the task to be added to file.
     */
    @Override
    public String toFile() {
        return "E" + FILE_STRING_SEPARATOR + super.toFile() + FILE_STRING_SEPARATOR + at + "\n";
    }

    /**
     * Returns if the event contains the input.
     *
     * @param input the input of the user.
     * @return returns whether input is contained in event.
     */
    @Override
    public boolean isInTask(String input) {
        boolean isInAt = at.toString().contains(input);
        boolean isInFormattedAt = at.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toLowerCase().contains(input);
        return super.isInTask(input) || isInAt || isInFormattedAt;
    }
}
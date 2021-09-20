import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * This represents the subclass Event under superclass Task in each element of Task[] list in Duke.java.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * This function initialises the deadline.
     *
     * @param description description input by user.
     * @param at date of event
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * This function modifies the output format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFile() {
        return "E" + FILE_STRING_SEPARATOR + super.toFile() + FILE_STRING_SEPARATOR + at + "\n";
    }

    @Override
    public boolean isInTask(String input) {
        boolean isInAt = at.toString().contains(input);
        boolean isInFormattedAt = at.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toLowerCase().contains(input);
        return super.isInTask(input) || isInAt || isInFormattedAt;
    }
}
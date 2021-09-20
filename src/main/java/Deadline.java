import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * This represents the subclass Deadline under superclass Task in each element of Task[] list in Duke.java.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * This function initialises the deadline.
     *
     * @param description description input by user.
     * @param by date to complete event by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * This function modifies the output format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFile() {
        return "D" + FILE_STRING_SEPARATOR + super.toFile() + FILE_STRING_SEPARATOR + by + "\n";
    }
}
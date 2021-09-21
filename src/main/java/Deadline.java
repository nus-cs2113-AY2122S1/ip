import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Initialises the deadline.
     *
     * @param description description input by user.
     * @param by          date to complete event by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Modifies the output format.
     *
     * @return the desired output format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Modifies the string format for adding to file.
     *
     * @return the correct format for the task to be added to file.
     */
    @Override
    public String toFile() {
        return "D" + FILE_STRING_SEPARATOR + super.toFile() + FILE_STRING_SEPARATOR + by + "\n";
    }

    /**
     * Returns if the deadline contains the input.
     *
     * @param input the input of the user.
     * @return returns whether input is contained in deadline.
     */
    @Override
    public boolean isInTask(String input) {
        boolean isInBy = by.toString().contains(input);
        boolean isInFormattedBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toLowerCase().contains(input);
        return super.isInTask(input) || isInBy || isInFormattedBy;
    }
}
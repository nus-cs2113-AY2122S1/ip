/**
 * This represents the subclass Deadline under superclass Task in each element of Task[] list in Duke.java.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * This function initialises the deadline.
     *
     * @param description description input by user.
     * @param by date to complete event by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This function modifies the output format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFile() {
        return "D" + FILE_STRING_SEPARATOR + super.toFile() + FILE_STRING_SEPARATOR + by + "\n";
    }

    @Override
    public boolean isInTask(String input) {
        boolean isInBy = by.contains(input);
        return super.isInTask(input) || isInBy;
    }
}
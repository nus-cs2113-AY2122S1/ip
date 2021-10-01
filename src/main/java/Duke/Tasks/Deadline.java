package Duke.Tasks;

public class Deadline extends Task {
    public String by;

    /**
     * Create a Deadline class with given description and by time.
     *
     * @param description Description of the deadline task.
     * @param by By time of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Present deadline in string of prescribed format.
     *
     * @return String presented deadline description format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by
                + ")";
    }
}

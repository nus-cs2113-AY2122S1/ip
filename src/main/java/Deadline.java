public class Deadline extends Task {
    String by;
    public static final String PARAMETER_BY = "/by";

    /**
     * Creates a new Task object to be stored in Duke's list of Tasks,
     * sets the name of the task as the name passed in by the user and
     * marks the task as incomplete
     *
     * @param description the name of the task to be created
     * @param by the date or time by the task is to be done
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

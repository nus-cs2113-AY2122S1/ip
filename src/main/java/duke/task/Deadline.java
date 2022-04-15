package duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline as a string to be written in a data file.
     *
     * @return Task in text format e.g. "D | 0 | [description] | [due date]".
     */
    @Override
    public String toText() {
        return "D " + super.toText() + " | " + by;
    }

    /**
     * Returns the deadline as a string to be displayed in the terminal for the user
     * to read the task in string format.
     *
     * @return Task in string format e.g. "[D][X] [description] (by: [due date])".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
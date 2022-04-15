/**
 * A type of task with a date element to it
 */
public class Deadline extends Task {

    private String by;

    /**
     * Constructor for Deadline
     *
     * @param description what the deadline is about
     * @param isDone      whether the deadline has been done or not
     * @param by          when the deadline is
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Builds on the string that is formatted for DukeData/data.txt for tasks
     *
     * @return String for storing in DukeData/data.txt
     */
    public String getDataStorageString() {
        return 'D' + super.getDataStorageString()
                + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by + ")";
    }
}

package task;

public class Deadline extends Task {
    protected String byWhen;

    /**
     * Class constructor with the specified task name
     * and when the Deadline is.
     *
     * @param taskName The name of the Deadline
     * @param byWhen The date and time of the Deadline
     */
    public Deadline(String taskName, String byWhen) {
        super(taskName);
        this.byWhen = byWhen;
    }

    /**
     * Class constructor with the specified task name, isDone value and when the Deadline is.
     * Mainly used for creating a Deadline using values loaded from file.
     *
     * @param taskName The name of the Deadline
     * @param isDone The boolean of whether Deadline is done
     * @param byWhen The date and time of the Deadline
     */
    public Deadline(String taskName, boolean isDone, String byWhen) {
        super(taskName, isDone);
        this.byWhen = byWhen;
    }

    /**
     * Returns the date and time of the Deadline.
     *
     * @return The date and time of the Deadline
     */
    public String getByWhen() {
        return byWhen;
    }

    /**
     * Returns the String-formatted Deadline for printing.
     *
     * @return The string-formatted Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By -> " + byWhen + ")";
    }
}

package duke;

public class Deadline extends Task {
    /**
     * Stores the deadline of the task
     */
    public String by;

    /**
     * Constructor to initialize task description and deadline.
     *
     * @param description Description stores the description of the task.
     * @param by          By stores the deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = TaskType.DEADLINE;
    }

    /**
     * returns the string in a particular format.
     *
     * @return Task description and deadline in a particular format.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by:" + this.by + ")");
    }

}

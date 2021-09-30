package duke.task;

public class Deadline extends Task {
    /** Task deadline. */
    private final String schedule;

    /**
     * Initializes the task description and deadline, and
     * sets initial status to "not done".
     *
     * @param description task description
     * @param schedule task deadline
     */
    public Deadline(String description, String schedule) {
        super(description);
        this.schedule = schedule;
    }

    /**
     * Initializes the task description and deadline, and
     * sets initial status according to the given parameter.
     *
     * @param description task description
     * @param schedule task deadline
     * @param isDone initial status
     */
    public Deadline(String description, String schedule, boolean isDone) {
        super(description, isDone);
        this.schedule = schedule;
    }

    public String getSchedule() {
        return schedule;
    }

    @Override
    public String serialize() {
        return DEADLINE + " | " + (isDone ? "1" : "0") + " | " + description + " | " + schedule;
    }

    @Override
    public String toString() {
        return "[" + DEADLINE + "]" + super.toString() + " (by: " + schedule + ")";
    }
}

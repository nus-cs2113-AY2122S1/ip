package duke.task;

public class Event extends Task {
    /** Task period. */
    private final String schedule;

    /**
     * Initializes the task description and deadline, and
     * sets initial status to "not done".
     *
     * @param description task description
     * @param schedule time period of the task
     */
    public Event(String description, String schedule) {
        super(description);
        this.schedule = schedule;
    }

    /**
     * Initializes the task description and deadline, and
     * sets initial status according to the given parameter.
     *
     * @param description task description
     * @param schedule time period of the task
     * @param isDone initial status
     */
    public Event(String description, String schedule, boolean isDone) {
        super(description, isDone);
        this.schedule = schedule;
    }

    public String getSchedule() {
        return schedule;
    }

    @Override
    public String serialize() {
        return EVENT + " | " + (isDone ? "1" : "0") + " | " + description + " | " + schedule;
    }

    @Override
    public String toString() {
        return "[" + EVENT + "]" + super.toString() + " (at: " + schedule + ")";
    }
}

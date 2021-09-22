package duke.task;

public class Todo extends Task {
    /**
     * Initializes task description and set the initial
     * status to "not done".
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initializes task description and sets the initial
     * status according to the given parameter.
     *
     * @param description Task description.
     * @param isDone Initial status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String serialize() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + TODO + "]" + super.toString();
    }
}
